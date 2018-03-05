# cuba-message

## 导出
先用7zip把studio-backend-6.8.2.jar 中的 com\haulmont\studio\common\messages\文件导出。
## 解密
调用Message.dealAllMessage("d:\\studio\\message6.7.7\\",true);
## 汉化
把解密以后的文件翻译完成
## 加密
调用Message.dealAllMessage("d:\\studio\\message2\\",false);加密
## 替换
把加密之后的文件用7zip替换studio-backend-6.8.2.jar 中的 com\haulmont\studio\common\messages\
