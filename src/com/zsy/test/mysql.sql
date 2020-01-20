//账号表
CREATE TABLE accounts(
  ACCOUNT_UID VARCHAR(100) primary key,   //账户未知标识ID,主键
  ACCOUNT_NAME VARCHAR(100) not null,     //账户名称
  ACCOUNT_PASSWORD VARCHAR(100) not null, //账户密码
  ACCOUNT_NICKNAME VARCHAR(100) not null, //账户昵称
  ACCOUNT_TYPR VARCHAR(100) not null,     //账户类型
  ACCOUNT_DATE DATE not null,             //创建时间
  ACCOUNT_CREATE_ID VARCHAR(100),         //创建者ID
)
//插入数据
INSERT INTO accounts VALUES ('uid2','账号2','密码2','昵称2','类型2','2009-06-08','uid1');


//创建用户反馈意见表
CREATE TABLE opinion(
  OID VARCHAR(100) primary key,       //反馈唯一标识ID,主键
	CREATER_NAME VARCHAR(100) not null, //用户名字
	CREATER_ID VARCHAR(100) not null,   //用户ID
	REMAKES LONGTEXT not null,          //反馈描述
	PICTURE VARCHAR(100),               //反馈图片路径
	CREATE_DATE DATE ,                  //创建日期
	IS_REJECTED BLOB,                   //是否驳回标志
  IS_AUDIT BLOB,                      //是否审核标志
  AUDIT_ID VARCHAR(100),              //审核人ID
  AUDIT_NAME VARCHAR(100),            //审核人名称
  AUDIT_DATE DATE ,                   //审核日期
  OPINION_REPLY_ID VARCHAR(100) ,     //产品对问题的回复人ID
  OPINION_REPLY_NAME VARCHAR(100) ,   //产品对问题的回复NAME
  OPINION_REPLY LONGTEXT ,            //产品对问题的回复
  OPINION_REPLY_DATE DATE,            //产品对问题的回复日期
  COMPLETE_REPLY_ID VARCHAR(100) ,    //产品在解决后回复人ID
  COMPLETE_REPLY_NAME VARCHAR(100) ,  //产品在解决后回复NAME
  COMPLETE_REPLY LONGTEXT,            //产品在解决后回复
  COMPLETE_REPLY_DATE DATE            //产品在解决后回复日期
);

//反馈意见相关图片子表
CREATE TABLE opinion_picture(
 PID VARCHAR(100) not null,                                         //图片ID
 PICTURE_PATH VARCHAR(100) not null,                                //图片路径
 constraint TASK_PICTURES foreign key(PID) references opinion(TID)  //外键关联任务主键
)

//任务表
CREATE TABLE task(
  TID VARCHAR(100) primary key,         //任务唯一标识ID,主键
	CREATER_NAME VARCHAR(100) not null,   //创建者名字
	CREATER_ID VARCHAR(100) not null,     //创建者ID
	TASK_DATE DATE not null,              //创建时间
	REMAKES LONGTEXT not null,            //任务描述
	DEVELOPER_NAME VARCHAR(100),          //指派开发者名称
	DEVELOPER_ID VARCHAR(100),            //指派开发者ID
	TASK_OPEN BOOL not null,              //开启任务标志
	TASK_FINISH BOOL not null,            //完成任务标志
	TASK_TEST BOOL not null,              //测试任务标志
	ASSOCIATED_TASK VARCHAR(100),         //关联任务标识(关联其他任务)
	ORIGIN_ID VARCHAR(100),               //上游反馈关联ID
	ORIGIN_NAME VARCHAR(100),             //上游反馈关联NAME
	LEVEL_NAME VARCHAR(100) not null,     //任务等级名称
	LEVEL_ID INT not null,                //任务等级ID
	TYPE_NAME VARCHAR(100) not null,      //任务类型名称(BUG/需求)
	TYPE_ID INT not null  ,               //任务类型ID(BUG/需求)
	LENGTH_TOTAL FLOAT not null,          //任务类型总时长
	LENGTH_CURRENT FLOAT not null         //任务类型当前进度
);

//插入数据
INSERT INTO task VALUES ('task1','账号name2','账号id2','2009-06-08','这是任务描述','指派name','指派id',true,false,false,'tid0','反馈id','原始任务','紧急','1','需求','1',100,50);

//任务相关图片路径储存地址子表(依赖于任务,主键-外键)
CREATE TABLE task_pictures(
 PID VARCHAR(100) not null,                                      //图片ID
 PICTURE_PATH VARCHAR(100) not null,                             //图片路径
 constraint TASK_PICTURES foreign key(PID) references task(TID)  //外键关联任务主键
)

//测试记录ID
CREATE TABLE test(
 SID VARCHAR(100) not null,       //测试记录ID
 TEST_TEXT VARCHAR(100) not null, //测试标题
 TEST_TEXT LONGTEXT,              //测试描述
 TEST_DATE DATE                   //测试日期
 constraint TASK_TEST foreign key(SID) references task(TID)  //外键关联任务主键
)

//测试相关图片路径储存地址子表(依赖于任务,主键-外键)
CREATE TABLE test_picture(
 PID VARCHAR(100) not null,                                            //图片ID
 PICTURE_PATH VARCHAR(100) not null,                                   //图片路径
 constraint TASK_PICTURES foreign key(PID) references test_history(SID)//外键关联任务主键
)

//记录某任务指派历史子表
CREATE TABLE task_assign(
  HID VARCHAR(100) not null,                    //指派记录ID
  ASSIGN_ID VARCHAR(100) not null,              //指派账号ID
  ASSIGN_NAME VARCHAR(100) not null,            //指派账号Name
  ASSIGN_DATE DATA not null,                    //指派日期
  constraint TASK_PICTURES foreign key(HID) references task(TID)  //外键关联任务主键
)

//记录某任务进度历史子表
CREATE TABLE task_progress(
  PID VARCHAR(100) not null,                      //进度记录ID
  progress_ID VARCHAR(100) not null,              //进度记录账号ID
  progress_NAME VARCHAR(100) not null,            //进度记录账号Name
  progress_DATE DATA not null,                    //进度记录日期
  progress_content LONGTEXT,                      //进度记录描述
  constraint TASK_PROGRESS foreign key(PID) references task(TID)  //外键关联任务主键
)





