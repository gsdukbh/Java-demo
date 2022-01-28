

```shell

# 获取token

curl -XPOST user:password@localhost:8080/token

export TOKEN = `curl -XPOST user:password@localhost:8080/token`

# 使用token
curl -H "Authorization: Bearer $TOKEN" localhost:8080/

# echo 
# hello world user
```