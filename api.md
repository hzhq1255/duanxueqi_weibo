## 1. 用户

### /login

**method** POST

| 参数   | 描述 |      |
| ------ | ---- | ---- |
| name |      |      |
| pwd  |      |      |
|        |      |      |

**返回**

userId 

### /reg

**method** POST

| 参数     | 描述 |      |
| -------- | ---- | ---- |
| name   |      |      |
| pic    |      |      |
| gender |      |      |
| pwd1    |      |      |
| pwd2    |      |      |
| des    |      |      |
| birth  |      |      |
|          |      |      |

**返回**

success error

### /getUserInfo

**method** GET

| 参数   |      |      |
| ------ | ---- | ---- |
| userId |      |      |

**返回**

带有用户信息的 result

## 2.微博

### /getAllWeibo

**method** GET

| 参数        | 描述             |      |
| ----------- | ---------------- | ---- |
| userId      | 当前登录的用户id |      |
| currentPage |                  |      |
| pageSize    |                  |      |

无参 带评论 回复

**返回**

微博 信息 点赞数 评论数 当前用户是否喜欢

### /getMyWeibo

**method** GET

| 参数        | 描述             |      |
| ----------- | ---------------- | ---- |
| userId      | 当前登录的用户id |      |
| currentPage |                  |      |
| pageSize    |                  |      |

无参 带评论 回复

**返回**

微博 信息 点赞数 评论数 当前用户是否喜欢

### /getLikeWeibo

**method** GET

| 参数        | 描述             |      |
| ----------- | ---------------- | ---- |
| userId      | 当前登录的用户id |      |
| currentPage |                  |      |
| pageSize    |                  |      |

**返回**

同上

### /sendWeibo

**method** POST

如果传入source 则代表转发

| 参数    | 描述             |      |
| ------- | ---------------- | ---- |
| userId  | 当前登录的用户id |      |
| content | 评论内容         |      |
| tag     | 标签             |      |
| source  | 转发微博         |      |

**返回**

result success

### /delWeibo

**method**：post

| 参数    | 描述 |      |
| ------- | ---- | ---- |
| weiboId |      |      |
|         |      |      |
|         |      |      |

**return**

error or success

## 评论

### /getAllCommnetByWeiboId

**method** GET

| 参数        | 描述    |      |
| ----------- | ------- | ---- |
| weiboId     | weiboId |      |
| currentPage |         |      |
| pageSize    |         |      |

**return**

评论列表

### /getAllCommnetByUserId

**method** GET

| 参数        | 描述 |      |
| ----------- | ---- | ---- |
| userId      |      |      |
| currentPage |      |      |
| pageSize    |      |      |

**return**

用户评论列表

### /sendComment

**method**: post

| 参数    | 描述 |      |
| ------- | ---- | ---- |
| weiboId |      |      |
| userId  |      |      |
| content |      |      |

**返回**

success or error

### /delComment

**method**: post

| 参数      | 描述 |      |
| --------- | ---- | ---- |
| commentId |      |      |
|           |      |      |
|           |      |      |

**返回**

success or error

## 点赞

### /addLike

**method** post

**desc**:

三种情况

userId , weiboId 不为空 其他为空 对微博点赞

userId,  commentId 不为空 其他为空 对评论点赞

userId,  replyId 不为空 其他为空 对回复点赞

其他情况一律报错

| 参数      | 描述   |      |
| --------- | ------ | ---- |
| userId    | 不为空 |      |
| weiboId   |        |      |
| commentId |        |      |
| replyId   |        |      |

**return**

success or error

### /cancelLike

**method** post

**desc**:

三种情况

userId , weiboId 不为空 其他为空 取消微博点赞

userId,  commentId 不为空 其他为空 取消评论点赞

userId,  replyId 不为空 其他为空 取消回复点赞

其他情况一律报错

| 参数      | 描述   |      |
| --------- | ------ | ---- |
| userId    | 不为空 |      |
| weiboId   |        |      |
| commentId |        |      |
| replyId   |        |      |

**return**

success or error