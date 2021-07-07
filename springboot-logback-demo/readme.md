## [logback 文档](http://logback.qos.ch/documentation.html) 

[中文翻译文档](https://logbackcn.gitbook.io/logback/)

## [logstash 文档](https://www.elastic.co/guide/en/logstash/7.12/upgrading-logstash.html)
## [elasticsearch 文档](https://www.elastic.co/cn/elasticsearch/)
## [kibana 文档](https://www.elastic.co/cn/kibana/)


> 安装 logstash json插件
> ```shell
> docker exec -it logstash /bin/bash
> cd /bin
> logstash-plugin install logstash-codec-json
> exit
> docker restart logstash
> ```