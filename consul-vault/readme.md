# run with config

`vault server --config=consul-vault/vault-conf.hcl`

上記設定は、consul に設定を保存するようにしているので、consul の起動が必要になる。

initial setup　(https://www.vaultproject.io/docs/commands/operator/generate-root.html)

```
vault operator init
#-- save root token. unseal tokens
# input  unseal key three times.
vault operater unseal 
vault operater unseal
vault operater unseal

export VAULT_ADDR=http://127.0.0.1:8200  # for http access.
export VAULT_TOKEN=<roottoken>
vault secrets enable kv #path default kv/
vault secrets enable --path=secret/  kv
# create token for test
vault  token create --id=00000000-0000-0000-0000-000000000000 --policy=root
```

## note

+ kv を有効にすることで、 当該パスに対する write,read ができるようになる。
+ consul を storage backend にしてある場合、vaultの設定と kv の設定は、consulの KV の vault以下に設定される。
    + vault の kv は、 vault/logical 以下に暗号化されて配置
    + vault　を再起動しても設定は残る。

## spring cloud vault

http://cloud.spring.io/spring-cloud-vault/2.1.x/single/spring-cloud-vault.html

vault の kv を、secret/ で有効にしておく。
アプリケーション起動時に、　`secret/<spring.application.name>` または `secret/application` のキーを取得し、プロパティにセットする。
(`secret/<name>/<profile>` でプロファイルごとの値も設定可能)

`vault kv write secret/application example=1 hoge.v1=2 hoge.v2=3` のようにすると、
アプリケーションでは、　example, hoge.v1, hoge.v2 3つのプロパティを設定したことになる。

consul の KV config と異なり、secretの変更を契機に refresh されない。
(ただし、secretの lease に合わせて更新される可能性もある
https://docs.spring.io/spring-vault/docs/current/reference/html/index.html#vault.authentication.token
http://cloud.spring.io/spring-cloud-vault/2.1.x/single/spring-cloud-vault.html#vault-lease-renewal
未検証)

どうしても、secretの再読み込みをしたいなら、 refresh エンドポイントを明示的にコールする。

