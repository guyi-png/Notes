 ### 数据库存储的瓶颈
    数据量的总大小一个机器放不下
    数据的索引（b+树）一个机器的内存放不下
    访问量一个机器不能承受
 
 #### 
 noSql  不仅仅是sql
    数据之间无关系，这样就非常容易扩展，也无形之间，在架构的层面上带来了可扩展的能力
    
        
 ##### xx
    key and value
    cache
    persistence  
    键值对 - 缓存 - 持久化
    
    3V:  海量Volume 多样Variety 实时Velocity
    3高： 高并发 高可扩 高性能
    
    技术没有高低之分，  只有用技术的人有强弱之别
    
    customer  order orderitem payment product address
    
    CAP: consistency强一致性  Availability可用性 Partition tollerance分区容错性
    
    BASE: Basically Avaliable 基本可用  soft state 软状态 eventually consistent最终一致
  
### redis
[官网](https://redis.io/)

    remote dictionary server（远程字典服务器）
    是用c语言编写的一个高性能(键值对)的分布式内存数据库，基于内存运行并支持持久化的Nosql数据库    
    
    三个特点
    1. Redis支持数据的持久化,可以将内存中的数据保持在磁盘中,
    重启的时候可以再次加载进行使用
    2. Redis不仅仅支持简单的key-value类型的数据,
    同时还提供list, set, zset, hash等数据结构的存储
    3. Redis支持数据的备份,即master-slave模式的数据备份
    
    作用：
        内存存储和持久化: redis支持异步将内存中的数据写到硬盘上,同时不影响继续服务
        取最新N个数据的操作,如:可以将最新的10条评论的ID放在Redis的List集合里面
        模拟类似于HttpSession这种需要设定过期时间的功能
        发布、订阅消息系统
        定时器、计数器

###  安装
#### 第一步
wget http://download.redis.io/releases/redis-6.0.8.tar.gz
然后解压  tar xzf redis-6.0.8.tar.gz
cd redis-6.0.8.tar.gz 文件中
执行  make  命令
安装	    yum install gcc-c++
删除原来的  make distclean
重新安装    make
执行    install

#### 第二步
cp redis.conf /xx
到该目录更改redis.conf的副本
更改  daemonoze no  为  yes    （daemonoze守护进程）
redis-server  /xx/redis.conf
redis-cli -p 6379
可到 usr/local/bin中查看

#### 退出
shutdown
exit


## 基本介绍

### redis的数据类型
	String（字符串）：
		string是redis的最基本类型，一个key对应一个value
		string类型是二进制安全的，及redis的string可以包含任何数据，比如jpg图片或者序列化的对象
		string中字符串value最多可以是512M

	Hash （哈希， 类似于map）：
		是一个键值集合，是string类型的field和value的映射表，适合存储对象

	List（列表）：
		 底层是链表，list是简单的字符串列表，按照插入顺序排序，可以添加一个元素到list的头部或尾部

	Set（集合）：
		是string类型的无序集合，通过hashtable的原理实现

	Zset（sorted set， 有序集合）：
		zset和set一样是string类型元素，且不允许重复元素，但zset会给每个元素关联一个double类型的分数，
		redis通过分数来为集合中的成员进行从小到大的排序，zset的成员是唯一的，但分数（sorce）可以重复

		
redis索引从0开始

redis-conf中  可以设置databases的num

select index   切换对应的数据库（index从0开始， 最大为databases的num-1）

dbsize		查看当前数据库的key的数据

keys [pattern]    关键字，匹配符, * , ? etc 

flushdb		清空当前库

flushall	清空所有的库

move key db 从当前库移除



## 获取redis常见数据类型操作命令

#### string 相关

set 	设置键值对， 当key一样时会覆盖value   如   set key1 value1

get 	提供key获取值  如  get xx(xx是key)

expire key  time（单位秒） 设置过期时间

ttl key  查看还有多少秒过期，-1表示不过期，-2表示已过期

type key   查看指定可以的类型

del key    删除key-value

exists key    判断是否存在key， 0表示没有，1表示有

append key value   给key的value增加值

incr   key  给key对应的value数字 + 1 ，不是数字就不行

decr   key  给可以对应的value数字 - 1 ，不是数字就不行

incrby  key  value1  给key对应的value数字 + value1 ，不是数字就不行

decrby  key  value1  给key对应的value数字 - value1 ，不是数字就不行

getrange key preindex postindex  查看key对应的value的指定范围的内容，
如getrange xx 0 3 表示显示第一个到第四个（含第四个）位置的内容

setrange和getrange一样，一个是取，一个是设置

setex  key time value  （set with expire）设置键值对以及过期时间（单位秒）

setnx  key value  （set if not exists）如果不存在key，设置键值

mset/mget  key1 [value1] key2 [value2]   (more set)设置/获取多个键值对

msetnx  key1 value1 key2 value2   如果不存在key1，key2就创建多个键值对



#### list相关
首先想象一个双向链表
lpush listname val1 val2 val3 ...   从左边开始依次放入list中

rpush listname val1 val2 val3 ...   从右边开始依次放入list中

lpop listname  从左边取值

rpop listname  从右边取值

lrange  listname preindex postindex  获取指定范围的内容

lindex listname index 通过索引获取值

llen  获取list的长度

lrem key n val   删除n个val

ltrim key  preindex postindex 截取指定范围的值后再赋值给value，postindex包括在内

rpoplpush  list1 list2   从list1中出栈一个，再把这个入栈于list2

lset key index value 对list中指定index的value设值

linsert key before/after val1 val2 在val1的前面或后面添加val2



#### set 相关
sadd setname val1 val2 val3   添加元素

smembers setname   查看set所有成员

sismember setname val 判断set中有没有val

scard 获取集合里面的元素个数

srem key val 删除set中元素

srandmember key n   查看指定n个的随机成员

spop key n     指定n个的随机成员出栈

smove key1 key2  在key1中的值   将key1中的某个值赋给key2

sdiff set1 set2   差集， 结果集为 set1  - （set1和set2公共部分）
sinter set1 set2  交集， 结构集为 set1和set2公共部分
sunion set1 set2  并集， 结果集为 set1 + set2， set中元素不可重复



#### hash相关
键值对（key-{field-value}）中， 值为键值对(field-value)

hset key field value   创建hash， 如 hset user id 6， 若key和fiel都相同，则会替换value

hget key field   获取对应的值

hmset user id xx name xx age xx  创建hash并设置多个field

hmget user id name age  获取hash的多个field

hgetall key    获取key对应的所有field

hdel key field 删除key对应的某个field

hlen key 获取hash的field数量

hexists key field   判断hash的field是否存在

hkeys  获取hash所有的field

hvals   获取hash所有field对应的value

hincrby key field value val  让value的值增加val，前提是value是数字

hincrbyfloat key field value val 让value的值增加val，前提是value是浮点数字

hsetnx key field value   如果key对应的filed不存在就设值



##### zset 相关
在set基础上添加score值,

zadd key score member score member ... 创建zset添加，添加多个值，score得是数字

zrange key preindex postindex [withscores]  指定范围查询，可以带上withscores,查看scores

zrangebyscore key min max   指定score范围查询，   (  指定min或max不包含在内   limit start size 指定限制，最初从0开始，size是数量

zrem key member  删除zset的成员

zcard key  查看所有的成员的总数

zcount key min max 查看score在min，max范围的成员的总数

zrank key member  查看member的索引

zscore key member  查看member的score

zrevrange key preindex postindex 反转查看

zrevrangebyscore key  反转以score（score也要反过来）范围查看


### 解析配置文件
redis.conf配置项说明如下,
1. Redis默认不是以守护进程的方式运行,可以通过改配置项修改,使用yes启用守护进程
daemonize yes

2. 当Redis以守护进程方式运行时, Redis默认会把pid写入/var/run/redis.pid文件,可以通过pidfile指定
pidfile /var/run/redis.pid

3. 指定Redis监听端口,默认端口为6379,作者在自己的一篇博文中解释了为什么选用6379作为默认端口,因为6379在手机按键上MERZ对应的号码,而MERZ取自意大利歌女Alessia Merz的名字
port 6379

4. 排定的主机地址
bind 127.0.0.1

5. 当客户端闲置多长时间后关闭连接,如果指定为0,表示关闭功能
timeout 300

6. 指定日志记录级别. Redis总共支持四个级别, debug, verbose, notice, warning.默认为verbose
loglevel verbose

7. 日志记录方式,默认为标准输出,如果配置Redis为守护进程方式运行,而这里又配置为日志记录方式为标准输出,则日志将会发送给/dev/null
logfile stdout

8. 设置数据库的数量，默认为数据库为第 0 ， 可以使用select dbid(第几个数据库)命令连接上指定数据库id
databases 16

9. 指定在多长时间内，有多少更新操作，就将数据同步到数据文件，可以多个条件配合  
save seconds changes

10. 指定存储至本地数据库时是否压缩数据,默认为yes, Redis采用LZF压缩,如果为了节省CPU时间,可以关闭读选项,但会导致数据库文件变的巨大
rdbcopression yes

11. 指定本地数据库文件名,默认值为dump.rdb
dbfilename dump.rdb

12. 指定本地数据库存放目录
dir ./

13. 设置当本机为slav服务时,设置master服务的IP地址及端口,在Redis启动时,它会自动从master进行数据同步
slaveof masterip masterport

14. 当master服务设置了密码保护时, slav服务连接master的密码
masterauth master-password

15. 设置Redis连接密码,如果配置了连接密码,客户端在连接Redis时需要通过AUTH password令提供密码,默认关闭
requirepass foobared

16. 设置同一时间最大客户 连接数,默认无限制, Redis可以同时打开的客户端连接数为Redis进程可以打开的最大文件描述符数,如果设置maxclients 0,表示不作限制,当客户端连接数到达限制时, Redis会关闭新的连接并向客户端返图max number of clients reached错误信息
maxclients 128

17. 指定Redis最大内存限制, Redis在启动时会把数据加勃到内存中,达到最大内存后, Redis会先含试清除已到期或即将到期的Key,当此方法处理后,仍然到达最大内存设置,将无法再进行写入操作,但仍然可以进行读取操作. Redis新的vm机制,会把key存放内存, Value会存放在swap区
maxmemory bytes

18. 指定是否在每次更新操作后进行日志记录, Redis在默认情况下是异步的把救据写入磁盒,如果不开启,可能会在断电时导致一段时间内的数据丢失。因为redis本身同步数据文件是按上面save条件来同步的,所以有的数据会在一段时间内只存在于内存中.默认为no
appendonly no

19. 指定更新日志文件名,默认为appendonly.aof
appendfilename appendonly.aof

20. 指定更新日志条件,共有3个可选值no,表示等操作系统进行数据缓存同步到雄盒(快)always.表示每次更新操作后手动调用fsync()开数据写到继盒(慢,安全), everysec.表示每秒同步一次(折束,默认值)
appendfsync everysec

21. 指定是否启用虚拟内存机制,默认值为no. vM机制将数据分页存放,由Redis将访问量较少的页即冷数据swap到磁盘上,访问多的页面由磁盒自动换出到内存中
vm-enabled no

22. 虚拟内存文件路径,默认值为/tmp/redis.swap.不可多个Redis实例共享
vm-swap-file /tmp/redis.swap

23. 将所有大于vm-max-memory的数据存入虚拟内存,无论vm-max-memoryie置多小,所有素引数摆都是内存存储的(Redis的素引数据就是keys),也就是说,当vm-max-memoryi置为0的时候,其实是所有value都存在于磁盒,默认值为0
vm-max-memory 0

24. Redis swap文件分成了很多的page,一个对象可以保存在多个page上面,但一个page上不能被多个对象共享, vm-page-size是要根据存储的数据大小来设定的.作者建议如果存储很多小对象, page大小最好设置为32或者64bytes,如果存储很大大对象,则可以使用更大的page,如果不确定,就使用默认值
vm-page-size 32

25. 设置swap文件中的page数量,由于页表(一种表示页面空间或使用的bitmap)是在放在内存中的,.,在磁盒上每8个pages将消耗1byte的内存.
vm-pages 134217728

26. 设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的,可能会造成比较长时间的延迟.默认值为4
vm-max-threads 4

27. 设置在向客户端应答时,是否把较小的包合并为一个包发送,默认为开启
glueoutputbuf yes

28. 指定在超过一定的数量或者最大的元素超过某一临界值时,采用一种特殊的哈希法h
ash-max-zipmap-entries 64
hash-max-zipmap-value 512

29. 指定是否激活重置哈希,默认为开启
activerehashing yes

30. 指定包含其他的配置文件，可以在同一主机上多个redis实例之间使用同一份配置文件，而同时各个实例又拥有自己的特定配置文件
include /path/to/local.conf

。。。


## 持久化(persistence)
rdb (redis database)
在指定的时间间隔内将内存中的数据集快照写入磁盘也就是行话讲的Snapshot快照,它恢复时是将快照文件直接读到内存里。
Redis会单独创建(fork)一个子进程来进行持久化,会先将数据写入到一个临时文件中,待持久化过程都结束了,
再用这个临时文件替换上次持久化好的文件（dump.rdb）。整个过程中,主进程是不进行任何IO操作的,这就确保了极高的性能如果需要进行大规模数据的恢复,且对于数据恢复的完整性不是非常敏感,那RDB方式要比AOF方式更加的高效,RDB的缺点是最后一次持久化后的数据可能丢失
   fork: 作用是复制一个与当前进程一样的进程，新进程的所有数据（变量，环境变量，程序计数器等）数值都和原进程一致，但是是一个全新的进程，并作为原进程的子进程
可以立刻保存   save/bgsave     （save时只管保存，其他不管，全部阻塞， 使用bgsave时，redis会在后台异步地进行快照操作，同时还可以响应客户端请求，可以通过lastsave命令获取最后一次成功执行快照的时间）
shtdown 也会保存

aof (append only file)
以日志的形式记录每个写的操作，将redis执行过的所有写指令记录下来（不记录读的操作），只许追加文件但不可以改写文件，redis启动之初
会读取该文件重新构建数据，即redis重启的话就根据日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作


rdb的dump.rdb文件和aof的appndonly.aof文件两者可以都存在，重新构建数据时会率先找appendonly.aof
如果文件因为意外文件中出现乱码，可以通过 redis-check-rdb或redis-check-aof 修复


## redis的事务， 部分支持事务
开启： 以multi开始一个事务
入队： 将多个命令入队到事务中， 接到这些命令并不会立即执行，而是放到等待执行的事务队列里面
执行： 有exec命令触发事务


multi    开启事务
当开启事务后，执行的命令都会入队
通过 exec  执行事务
discard   放弃事务 

在事务时出现错误，将会discard，放弃事务的所有命令
如 对非数字incr时产生的执行错误等，并不会影响事务中的其他命令

悲观锁： 每次去拿数据的时候都会认为别人会修改，所以每次拿数据的时候都会上锁，这样别人想拿这个数据时就会被block直到拿到锁

乐观锁：每次去拿数据的时候都会认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据， 可以使用版本号机制，提交版本必须大于记录当前版本才能执行更新

watch key    监视一个过多个key，如果在事务执行之前这个或这些key被其他命令所改动，那么事务将会被打断

unwatch 可以取消watch监控对所有key的监视


#### 事务的发布订阅
订阅了发布时才能接收消息
进程间的一种消息通信模式： 发送者（pub）发送消息，订阅者（sub）接收消息 

subscribe key key key ..  订阅channel
psubscribe xx    订阅多个
publish key message   发送消息


### redis的复制机制
主从复制，主机数据更新后根据配置和网络，自动同步到备机的master/slaver机制，master以写为主，slaver以读为主

info replication   查看主从复制消息

slaveof IP地址：端口号  
奴属于对应的redis-server，一旦奴属后，主的信息就都知道了，自己是不能写的。
如果master服务关闭，slaver仍然可以知道master的信息，slaver还是slaver，
但slaver服务关闭后就不再奴属于任何matser。
如果slaver也有奴隶，则主的信息可以被奴隶的奴隶知道

slaveof no one  使当前数据库停止与其他数据库的同步，转成主数据库

复制原理： Slave启动成功连接到master后会发送一个sync命令
Master接到命令启动后台的存盘进程,同时收集所有接收到的用于修改数据集命令,
在后台进程执行完毕之后, master将传送整个数据文件到slave,以完成一次完全同步
全量复制: slave服务在接收到数据库文件数据后,将其存盘并加载到内存中。
增量复制: Master继续将新的所有收集到的修改命令依次传给slave,完成同步但是只要是重新连接master,一次完全同步(全量复制)将被自动执行


哨兵模式： 能够后台监控主机是否故障，如果故障了根据投票数自动将从库转换为主库
创建配置文件 sentinel.conf 在里面写入 
sentinel monitor 被监控数据库的名字  主机的ip加端口  1（投票）
启动哨兵： redis-sentinel  sentinel.conf
当master关闭服务后，哨兵服务选出新的master，而原来的master回来时，就只能当奴隶      