
[documentation](https://dev.mysql.com/doc/refman/8.0/en/replication.html)


## 开始
进入控制执行
```Bash
# MASTER 
mysql -u root -p123456
# 记录binlog 
SHOW MASTER STATUS;


# slave 
mysql -u root -p123456
# 获取当前 server_id
SHOW VARIABLES LIKE 'server_id';
SET GLOBAL server_id = 2;
# 添加复制账号
mysql> CHANGE MASTER TO
    ->     MASTER_HOST='source_host_name',
    ->     MASTER_USER='replication_user_name',
    ->     MASTER_PASSWORD='replication_password',
    ->     MASTER_LOG_FILE='recorded_log_file_name',
    ->     MASTER_LOG_POS=recorded_log_position;

#Or from MySQL 8.0.23:
mysql> CHANGE REPLICATION SOURCE TO
    ->     SOURCE_HOST='source_host_name',
    ->     SOURCE_USER='replication_user_name',
    ->     SOURCE_PASSWORD='replication_password',
    ->     SOURCE_LOG_FILE='recorded_log_file_name',
    ->     SOURCE_LOG_POS=recorded_log_position;
# 启动从数据库
START SLAVE;
#显示状态
SHOW SLAVE STATUS\G;
#
#   Slave_IO_Running: Yes
#   Slave_SQL_Running: Yes

```