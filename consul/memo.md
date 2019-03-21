# consul memo

## install

download zip and unzip set path.

## run

consul <subcommand> で、単発のクエリ実行

consul agent --data-dir=hoge でエージェント用のデーモン起動。
consul agent --server --data-dir=hoge でサーバー用のデーモン起動
どちらも 8500ポートが基本。server は3代の冗長構成、エージェントは各ノードに導入が基本。

テスト用途では、一台で起動可能。
consul agent --srver --data-dir=hoge --dev  --ui

ui オプションをつけると、ブラウザで参照・KV操作が可能。

## KeyValue

UI, `consul kv`, /v1/kv REST それぞれで KV の設定が可能。

## Service Registeration

`consul service`  PUT /v1/agent/service/register などで登録

## Watch

サービスの変更、KVの変更などを検知して、任意のコマンドやHTTP実行ができる。

`consul watch -type keyprefix -prefix "hoge/" cat`

などとすると、 KVStore の hoge/以下の変更時に、cat コマンドが実行される。
watch は、コマンドのほか、起動時の設定ファイルで指定する。

```
{...
    "watches": [
        {
            "type": "keyprefix",
            "prefix": "hoge/",
            "handler_type": "script",
            "args": ["jq"]
        }
    ]
}
```

設定ファイルの指定は、以下の通り
```
consul agent --config-dir <config_dir> --dev
```

### blocking query

https://www.consul.io/api/index.html#blocking-queries

HTTPエンドポイントにアクセスして、タイムアウトするか対象に変化が起きるまで待つ仕組み。
watch に近い使い方ができる。

kv に対して、値が変更されるまで待つまでは wait, index の療法を指定する.
http://localhost:8500/v1/kv/hoge?wait=10m&index=74

index は現在の値が持つindex以上の値を指定する。
現在のindexは、ブロックなしでAPIを呼び出した時の戻り値や、レスポンスヘッダに書かれる。
現在のindexは値が変更されるたびに増加し、ブロッキングクエリのパラメータのindexの値を超えると通知される。
そのため、指定するindexの値をあまりに大きい値にすると、変更が通知されずブロックし続ける。


## spring cloud consul

spring-cloud-consul を導入し、bootstrap.yaml に、sprint.application.mname, と consul関係の設定を記載すればOK。
consul サーバーが起動している前提で、
起動時に自身をconsul のサービスとして登録する。

また、BlockingQueryを定期的に発行して、サービスの変更をspringが監視している。
その結果でServiceDiscoveryの内容を変更可能だが、今回は envoyをw使っているので、いらないかもしれない。

と思ったけど、promethues のpull先をconsul のサービス一覧から取得可能なので残す。
promethues を考えると envoy の metricsも送れるように envoy も登録したい。
envoy はサイドカーとして consul agent と常にノードごとにペアで配置される想定なので、
最初から consul の設定ファイルに監視対象として入れるのが本番なら楽。

今回は、controleplane に接続してきた envoy の情報を consul API 経由でサービス登録する。
(-> これは無理。 admin port を xDSで取得する手段がなく、手動で設定が必要になる。
  あとは service discovery に登録されたサービスの内容から、sidecar のサービスを類推して登録するようなスクリプトを書く方法もある)

また、ヘルスチェックのためには、 actuator の設定が必要。

### distribute config

大事なのはこっち。
KVStoreの内容を、application propertyとして使用でき、BlockingQueryを使ってKVStoreの変更を検知したら、
自動的に /refresh を実行し、 RefreshScopeのBeanを再読み込みする。
ConfigrationProperty は 暗黙的に RefreshScope なので、
KVStoreと連携させたい設定は、 ConfigurationProperties にしておくのが楽。

KVStore のうち、
`config/<applicatio.name>/` の下が各アプリケーション固有の設定になり、
`config/application/` の下がアプリケーション全体の共通設定となる。

例えば、 `something.value` というプロパティを使いたい場合は、

`config/<applicatio.name>/something/value` か
`config/application/something/value` に値を設定する。

また、`config/<application>,<profile>/` を使うと、プロファイルごとの設定も可能になる。

テスト用に、RefreshTestControllerの /something エンドポイントがある。
KVStore がない場合は、application.properties の someting.value, somthing.common を使う。
KVStore に `config/controleplane/something/value` や `config/application/something/common` などの値を設定すると、自動的に refresh が行われる。(タイムラグが多少ある)

### xDS との連携

envoy xDS に渡す動的な設定も KVStore で管理したい。
そのためには、KVStore に対する変更検知を行い、変更後の値でもって xDS のキャッシュ更新を行う。
方法は二つ

1. controleplaen にキャッシュ更新APIを用意し、consulwatch を使ってKVStore の変更を持ってAPIをコールする
2. controleplane から BlockingQuery を発行して、KVStoreの変更を検知する。

2 はAPIを用意しなくて良いが、BlockingQueryのインデックス設定やタイムアウト処理が煩雑だったり、非同期処理の実装が必要。
1の方が容易だと思う。

