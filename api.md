## 1. 登录

### /login

| 参数   | 描述 |      |
| ------ | ---- | ---- |
| u_name |      |      |
| u_pwd  |      |      |
|        |      |      |

**返回**

u_id 

### /reg

| 参数     | 描述 |      |
| -------- | ---- | ---- |
| u_name   |      |      |
| u_pic    |      |      |
| u_gender |      |      |
| u_pwd    |      |      |
| u_des    |      |      |
| u_birth  |      |      |
|          |      |      |

**返回**

success error

### /getAllWeibo

无参 带评论 回复

**返回**



#### /getLikeWeibo

参数 

u_id

**返回**

同上



### /comment

|           |      |      |
| --------- | ---- | ---- |
| w_id      |      |      |
| u_id      |      |      |
| c_content |      |      |

**返回**

success error



### /**reply**

|           |      |      |
| --------- | ---- | ---- |
| c_id      |      |      |
| u_id      |      |      |
| to_u_id   |      |      |
| r_content |      |      |
|           |      |      |



### /retweet

|           |      |      |
| --------- | ---- | ---- |
| u_id      |      |      |
| w_source  |      |      |
| w_content |      |      |



### /sendWeibo

|           |      |      |
| --------- | ---- | ---- |
|           |      |      |
| u_id      |      |      |
| w_content |      |      |



### /delWeibo

|      |      |      |
| ---- | ---- | ---- |
| w_id |      |      |
|      |      |      |
|      |      |      |



### /like

|      |      |      |
| ---- | ---- | ---- |
| w_id |      |      |
| u_id |      |      |
|      |      |      |



### /**getUserInfo**

|      |      |      |
| ---- | ---- | ---- |
| u_id |      |      |
|      |      |      |
|      |      |      |

