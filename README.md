软件测试第二次作业 Selenium Test

1.	配置环境
本次实验需要用selenium对同学们的GitHub地址进行测试，我选择了在chrome浏览器下进行测试。
在chrome中安装Kalaton，并模拟单次测试的实际情况，导出Test Suite为java文件。
接下来安装chromeDriver，下载chromeDriver后，将文件放到/usr/local/bin路径下，并测试。
 
2.	测试实例编写
本次实验需要引入多个jar包，包括selenium, poi, junit等，同时需要注意jar包版本与环境的对应关系。
 
接下来就是对测试逻辑的编写，具体代码已经上传到GIthub：
3.	问题分析
在最后测试阶段遇到了诸多问题，其中之一就是编写测试时对xlsx文档中可能出现的情况考虑不周，如文档中出现了末尾空格、换行、出现、缺少“/”符号、无url或开头空格等特殊情况，这些未考虑到的情况导致了测试报错，在接下来的编写中，分别对这些情况做了处理。
 
在之后的软件测试实验中，应该对这些特殊情况，提前有所考虑，积累经验。
4.	实验结果
 
