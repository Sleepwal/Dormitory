# Dormitory
1. 创建Vue项目
1.1 使用Vue UI
命令行运行vue ui, 创建项目

选择项目的目录

文件夹命名

手动配置



打开Router和Vuex

关闭Linter

选择2.x, 打开历史模块


到插件点击添加插件

安装element插件

安装axios插件

2. 登录模块
2.1 登录前端页面
<template>
  <div>
    <div id="blank"></div>
    <fieldset>
      <legend>用户登录</legend>
      <el-form
        :model="userForm"
        :rules="rules"
        ref="userForm"
        label-width="100px"
        class="demo-userForm"
        text-align:
        center
        style="width: 500px; margin: 0px auto"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="userForm.username"
            type="text"
          ></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input
            type="password"
            placeholder="请输入密码"
            v-model="userForm.password"
          ></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="checkPass">
          <el-input
            type="password"
            placeholder="请输入确认密码"
            v-model="userForm.checkPass"
            autocomplete="off"
            show-password
          ></el-input>
        </el-form-item>

        <el-form-item label="用户类型" prop="userType">
          <el-radio-group v-model="userForm.userType">
            <el-radio label="dormitoryAdmin">宿管</el-radio>
            <el-radio label="systemAdmin">系统管理员</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('userForm')" 
          :loading="logining"
            >登录</el-button
          >
          <el-button @click="resetForm('userForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </fieldset>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    var validatePass = (rule, value, callback) => {
      const check = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (!check.test(value)) {
          callback(new Error("密码至少包含 数字和英文，长度8-16"));
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.userForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      logining: false,
      
      userForm: {
        username: "",
        password: "",
        checkPass: "",
        userType: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 3,
            max: 20,
            message: "长度在 3 到 20 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { validator: validatePass, trigger: "blur" },
        ],
        checkPass: [
          { validator: validatePass2, trigger: "blur" },
        ],

        userType: [
          { required: true, message: "请选择用户类型", trigger: "change" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.logining = true;
      
        } else {
          alert("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
fieldset {
  /* 表单页面居中，宽度50% ,legend颜色设置，legend圆角*/
  border: 2px solid #dcdfe6;
  text-align: center;
  border-radius: 8px;
  margin: 0 auto;
  width: 50%;
  padding-top: 50px;
}

#blank {
  height: 100px;
}
</style>



2.2 宿管登录后端接口
创建SpringBoot项目
添加Lombok

添加Spring Web

添加MySQL Driver

① 添加mybatis-plus依赖
<!-- mybatis-plus -->
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus-boot-starter</artifactId>
  <version>3.3.2</version>
</dependency>
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus-generator</artifactId>
  <version>3.3.2</version>
</dependency>
<dependency>
  <groupId>org.apache.velocity</groupId>
  <artifactId>velocity</artifactId>
  <version>1.7</version>
</dependency>
② mybatis-plus generator生成代码
package com.sleepwalker;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.HttpClientErrorException;

/**
* @package: com.sleepwalker
* @className: Main
* @author: SleepWalker
* @description: mybatis-plus generator
* @date: 12:07
* @version: 1.0
*/
public class Main {
    public static void main(String[] args) {
        //创建对象
        AutoGenerator autoGenerator = new AutoGenerator();

        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/dormitory");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("166");
        autoGenerator.setDataSource(dataSourceConfig);

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //System.getProperty("user.dir") 根目录
        globalConfig.setOutputDir("D:\\Programme1\\Java_Code\\Project\\Dormitory\\SpringBootProject\\dormitoryms" + "/src/main/java");
        globalConfig.setAuthor("SleepWalker");
        globalConfig.setOpen(false); //是否把文件夹打开
        //去掉Service的I
        globalConfig.setServiceName("%sService"); //%s是类名
        autoGenerator.setGlobalConfig(globalConfig);

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        //设置包名
        packageConfig.setParent("com.sleepwalker");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //数据库名
        strategyConfig.setInclude("absent", "building", "dormitory", "dormitory_admin",
                                  "moveout", "student", "system_admin");
        //下划线转驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //添加Lombok
        strategyConfig.setEntityLombokModel(true);
        autoGenerator.setStrategy(strategyConfig);

        //启动
        autoGenerator.execute();
    }
}
③ 控制器
状态码和数据用一个类存储
@Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
package com.sleepwalker.vo;

import lombok.Data;

/**
 * @package: com.sleepwalker.vo
 * @className: ResultVo
 * @author: SleepWalker
 * @description: 数据和状态码
 * @date: 15:57
 * @version: 1.0
 */
@Data
public class ResultVo<T> {
    private Integer code;
    private T data;
}

控制器
@RestController
@RequestMapping("/dormitoryAdmin")
public class DormitoryAdminController {
    @Autowired
    DormitoryAdminService dormitoryAdminService;

    /**
     * @param userForm: 
     * @return ResultVo
     * @author SleepWalker
     * @description 登录
     * @date  16:26
     */
    @GetMapping("/login")
    public ResultVo login(UserForm userForm) {
        return dormitoryAdminService.login(userForm);
    }
}
④ Service层
@Service
public class DormitoryAdminServiceImpl extends ServiceImpl<DormitoryAdminMapper, DormitoryAdmin> implements DormitoryAdminService {
    @Autowired
    DormitoryAdminMapper dormitoryAdminMapper;

    @Override
    public ResultVo login(UserForm userForm) {
        ResultVo<UserForm> resultVo = new ResultVo<>();

        //1.用户类型是否正确
        if(userForm.getUserType().equals("dormitoryAdmin")){
            resultVo.setCode(-3);
            return resultVo;
        }

        //2.用户是否存在
        QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userForm.getUsername());
        DormitoryAdmin dormitoryAdmin = dormitoryAdminMapper.selectOne(queryWrapper);
        if(dormitoryAdmin == null) {
            resultVo.setCode(-1);
        } else {
            //3.密码是否正确
            if(dormitoryAdmin.getPassword().equals(userForm.getPassword())) {
                resultVo.setCode(0);
                resultVo.setData(userForm); //设置表单数据
            } else {
                resultVo.setCode(-2);
            }
        }

        return resultVo;
    }
}
⑤ Mapper
Mapper继承BaseMapper

⑥ 配置文件
server:
  port: 8081
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/dormitory?serverTimezone=UTC
    username: root
    password: 166
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mapper-locations: com/sleepwalker/mapper/xml/*.xml
2.3 前后端对接
解决跨域问题
1. 在目标方法上加@CrossOrigin
2. 添加CROS过滤器
3. 实现WebMvcConfigure接口
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");

    }
}
前端axios请求
methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.logining = true;
          // 回调里用不了this
          let _this = this
          if(_this.userForm.userType === 'dormitoryAdmin') {
            _this.axios.get('http://localhost:8081/dormitoryAdmin/login', {params: _this.userForm}).then(function(resp) {
              _this.logining = false;
              if(resp.data.code == -1){
                _this.$alert('用户不存在!', '提示',  {
                  confirmButtonText: '确定'
                })
              } else if(resp.data.code == -2){
                _this.$alert('密码错误!', '提示',  {
                  confirmButtonText: '确定'
                })
              } else if(resp.data.code == 0) {
                _this.$alert('登录成功!', '提示',  {
                  confirmButtonText: '确定'
                })
              } else {
                _this.$alert('无响应!', '提示',  {
                  confirmButtonText: '确定'
                })
              }
            
            })
          } else {
            _this.$alert('用户类型错误!', '提示',  {
                  confirmButtonText: '确定'
                })
          }

          setTimeout(() => {
            this.logining = false;
          }, 2000);
        } else {
          alert("error submit!!");
          return false;
        }
      });
    },
2.4 系统管理员登陆
① Service层
/**
* <p>
*  服务实现类
* </p>
*
* @author SleepWalker
* @since 2022-10-09
*/
@Service
    public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminMapper, SystemAdmin> implements SystemAdminService {
        @Autowired
        private SystemAdminMapper systemAdminMapper;

        @Override
        public ResultVo login(UserForm userForm) {
            ResultVo<UserForm> resultVo = new ResultVo<>();

            //1.用户是否存在
            QueryWrapper<SystemAdmin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", userForm.getUsername());
            SystemAdmin systemAdmin = systemAdminMapper.selectOne(queryWrapper);
            if(systemAdmin == null) {
                resultVo.setCode(-1);
            } else {
                //2.密码是否正确
                if(systemAdmin.getPassword().equals(userForm.getPassword())) {
                    resultVo.setCode(0);
                    resultVo.setData(userForm); //设置表单数据
                } else {
                    resultVo.setCode(-2);
                }
            }
            return resultVo;
        }
    }
2.5 前端菜单路由
① 路由跳转
const routes = [
  {
    path: "/login",
    name: "登录",
    component: Login,
  },
  {
    path: "/systemAdmin",
    name: "系统管理员",
    component: SystemAdmin,
    children: [
      {
        path: "/dormitoryAdminManager",
        name: "宿管管理",
        component: DormitoryAdminManager,
      },
      {
        path: "/dormitoryAdminAdd",
        name: "添加宿管",
        component: DormitoryAdminAdd,
      },
    ],
  },
];
② 系统管理员页面
<template>
  <el-container class="home_container">
    <!-- 顶部 -->
    <el-header class="home_header">
      <div class="home_title">DORMS宿舍管理系统-系统管理员</div>
      <div class="home_userinfoContainer">
        <el-dropdown>
          <span class="el-dropdown-link home_userinfo">
            {{ admin.username }}
            <i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>

    <!-- 导航栏 -->
    <el-container>
      <el-aside class="home_aside" width="200px">
        <!-- 展开 收起 -->
        <el-radio-group v-model="isCollapse" style="margin-bottom: 5px;margin-top: 5px;"
         fill=''>
          <el-radio-button :label="false">展开</el-radio-button>
          <el-radio-button :label="true">收起</el-radio-button>
        </el-radio-group>

        <!-- router导航 -->
        <el-menu
          router
          :collapse="isCollapse"
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <!-- 宿管模块 -->
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-monitor"></i>
              <span slot="title">宿管模块</span>
            </template>
            <el-menu-item
              index="/dormitoryAdminAdd"
              :class="$route.path == '/dormitoryAdminAdd' ? 'is-active' : ''"
            >
              <i class="el-icon-folder-add"></i>添加宿管
            </el-menu-item>
            <el-menu-item
              index="/dormitoryAdminManager"
              :class="
                $route.path == '/dormitoryAdminManager' ? 'is-active' : ''
              "
            >
              <i class="el-icon-document-copy"></i>宿管管理
            </el-menu-item>
          </el-submenu>

          <!-- 学生模块 -->
          <el-submenu index="2">
            <template slot="title"
              ><i class="el-icon-user-solid"></i>
              <span slot>学生模块</span>
            </template>
            <el-menu-item
              index="/studentAdd"
              :class="$route.path == '/studentAdd' ? 'is-active' : ''"
            >
              <i class="el-icon-folder-add"></i>添加学生
            </el-menu-item>
            <el-menu-item index="/studentManager">
              <i class="el-icon-document-copy"></i>学生管理
            </el-menu-item>
          </el-submenu>

          <!-- 楼宇模块 -->
          <el-submenu index="3">
            <template slot="title"
              ><i class="el-icon-office-building"></i
              ><span slot>楼宇模块</span></template
            >
            <el-menu-item index="/buildingAdd">
              <i class="el-icon-folder-add"></i>添加楼宇
            </el-menu-item>
            <el-menu-item index="/buildingManager">
              <i class="el-icon-document-copy"></i>楼宇管理
            </el-menu-item>
          </el-submenu>

          <!-- 宿舍模块 -->
          <el-submenu index="4">
            <template slot="title"
              ><i class="el-icon-s-home"></i>
              <span slot>宿舍模块</span></template
            >
            <el-menu-item index="/dormitoryAdd">
              <i class="el-icon-folder-add"></i>添加宿舍
            </el-menu-item>
            <el-menu-item index="/dormitoryManager">
              <i class="el-icon-document-copy"></i>宿舍管理
            </el-menu-item>
          </el-submenu>

          <el-menu-item index="/moveoutRegister">
            <i class="el-icon-s-unfold"></i>
            <span slot="title">学生迁出登记</span>
          </el-menu-item>

          <el-menu-item index="/moveoutRecord">
            <i class="el-icon-s-order"></i>
            <span slot="title">学生迁出记录</span>
          </el-menu-item>

          <el-menu-item index="/absentRecord">
            <i class="el-icon-error"></i>
            <span slot="title">学生缺寝记录</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <!-- 中间main部分 -->
      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item
              v-text="this.$router.currentRoute.name"
            ></el-breadcrumb-item>
          </el-breadcrumb>
          <router-view></router-view>
        </el-main>

        <!-- footer部分 -->
        <el-footer class="home_footer">2022 © SleepWalker</el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import AboutView from "./AboutView.vue";
export default {
  components: { AboutView },
  name: "SystemAdmin",
  data() {
    return {
      admin: '',
      isCollapse: true,
    };
  },
  methods: {
    logout() {
      this.$confirm('退出登录?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消', 
        type: 'warning'
      }).then(()=>{
        localStorage.removeItem('systemAdmin')
        this.$router.replace({path: '/login'})
      })
    },
  },
  created() {
    this.admin = JSON.parse(window.localStorage.getItem('systemAdmin'))
  }
};
</script>

<style>
.home_container {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
}

.home_header {
  background-color: #0b0332b3;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.home_title {
  color: #c2c2c2;
  font-size: 22px;
  display: inline;
}

.home_userinfo {
  color: rgb(195, 226, 68);
  cursor: pointer;
}

.home_userinfoContainer {
  display: inline;
  margin-right: 20px;
}

.home_aside {
  background-color: #daeae2;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.home_footer {
  background-color: #ffffff;
  color: #000000;
  font-size: 18px;
  line-height: 60px;
  text-align: center;
}
</style>

3. 宿管模块

3.1 添加宿管
① 页面
<template>
     <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;" >
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="ruleForm.password"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <div style="width: 170px;height: 30px;">
                    <template>
                        <el-radio v-model="ruleForm.gender" label="男">男</el-radio>
                        <el-radio v-model="ruleForm.gender" label="女">女</el-radio>
                    </template>
                </div>
            </el-form-item>
            <el-form-item label="联系电话" prop="telephone">
                <el-input v-model.number="ruleForm.telephone"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: 'DormitoryAdminAdd',
     data() {
            return {
                category:null,
                ruleForm: {
                    username: '',
                    password: '',
                    name: '',
                    gender: '',
                    telephone: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ],
                    gender: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    telephone: [
                        { required: true, message: '请输入联系电话', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8181/dormitoryAdmin/save',this.ruleForm).then((resp)=>{
                            if(resp.data.code == 0){
                                this.$alert(this.ruleForm.username+'添加成功', '', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        this.$router.push('/dormitoryAdminManager')
                                    }
                                });
                            }
                        })
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
}
</script>

<style>

</style>
② 处理添加
结果工具类
public class ResultVOUtil {
    /**
* @param object: 
* @return ResultVo
* @author SleepWalker
* @description 成功,code为0
* @date  20:27
*/
    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setData(object);
        return resultVo;
    }

    /**
* @param : 
* @return ResultVo
* @author SleepWalker
* @description 失败, 码为-1
* @date  20:28
*/
    public static ResultVo fail() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        return resultVo;
    }
}
控制器
@PostMapping("/save")
public ResultVo save(@RequestBody DormitoryAdmin dormitoryAdmin) {
    boolean save = dormitoryAdminService.save(dormitoryAdmin);
    if(save)
        return ResultVOUtil.success(null);
    else
        return ResultVOUtil.fail();
}
③ 结果


3.2 分页查询
① 前端视图
<template>
    <div style="margin-top: 60px;margin-left:80px;border: 0px solid red;" >
        <el-form style="margin-left: -40px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="字段：" prop="key">
                <el-select v-model="ruleForm.key" style="width: 160px;float: left" placeholder="请选择字段">
                    <el-option label="宿管账号" value="username"></el-option>
                    <el-option label="宿管姓名" value="name"></el-option>
                    <el-option label="宿管电话" value="telephone"></el-option>
                </el-select>
            </el-form-item>
            <div style="border: 0px solid red;width: 400px;height: 60px;position: relative;top: -64px;left: 270px">
                <el-form-item label="值：">
                    <el-input v-model="ruleForm.value" placeholder="请输入关键字" style="width: 160px;"></el-input>
                    <el-button type="primary" icon="el-icon-search" style="position: relative;left: 10px;" @click="submitForm('ruleForm')">搜索</el-button>
                </el-form-item>
            </div>
        </el-form>

        <el-table
                :data="tableData"
                border
                stripe
                style="width: 100%;position: relative;top:-30px">
            <el-table-column
                    fixed
                    prop="id"
                    label="ID"
                    width="160">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="用户名"
                    width="160">
            </el-table-column>
            <el-table-column
                    prop="password"
                    label="密码"
                    width="160">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="160">
            </el-table-column>
            <el-table-column
                    prop="gender"
                    label="性别"
                    width="160">
            </el-table-column>
            <el-table-column
                    prop="telephone"
                    label="联系电话"
                    width="160">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="edit(scope.row)">编辑</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="del(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 20px;float: right"
                       background
                       layout="prev, pager, next"
                       :page-size="pageSize"
                       :total="total"
                       :current-page.sync="currentPage"
                       @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData:null,
                currentPage: 1,
                pageSize: 3,
                total: 0,
                key: '',
                value: '',
                ruleForm: {
                    key: '',
                    value: '',
                    page: '',
                    size: 3
                },
                rules: {
                    key: [
                        { required: true, message: '请选择字段', trigger: 'change' }
                    ]
                }
            }
        },
        methods:{
            submitForm(formName) {
                const _this = this
                //让翻页复原
                _this.currentPage = 1
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this = this
                        _this.ruleForm.page = _this.currentPage
                        axios.get('http://localhost:8181/dormitoryAdmin/search',{params:_this.ruleForm}).then(function (resp) {
                            _this.tableData = resp.data.data.data
                            _this.total = resp.data.data.total
                        })
                    }
                });
            },
            page(currentPage){
                const _this = this
                if(_this.ruleForm.value == ''){
                    axios.get('http://localhost:8081/dormitoryAdmin/list/'+currentPage+'/'+_this.pageSize).then(function (resp) {
                        _this.tableData = resp.data.data.data
                        _this.total = resp.data.data.total
                    })
                } else {
                    _this.ruleForm.page = _this.currentPage
                    axios.get('http://localhost:8081/dormitoryAdmin/search',{params:_this.ruleForm}).then(function (resp) {
                        _this.tableData = resp.data.data.data
                        _this.total = resp.data.data.total
                    })
                }
            },
            edit(row){
                this.$router.push('/dormitoryAdminUpdate?id='+row.id)
            },
            del(row) {
                const _this = this
                this.$confirm('确认删除【'+row.username+'】吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete('http://localhost:8081/dormitoryAdmin/deleteById/'+row.id).then(function (resp) {
                        if(resp.data.code==0){
                            _this.$alert('【'+row.username+'】已删除', '', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    location.reload()
                                }
                            });
                        }
                    });
                });
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8081/dormitoryAdmin/list/1/'+_this.pageSize).then(function (resp) {
                _this.tableData = resp.data.data.data
                _this.total = resp.data.data.total
            })
        }
    }
</script>

<style>

</style>
② 后端
分页配置类
package com.sleepwalker.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageConfiguration {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}

Service
@Override
public PageVO list(Integer page, Integer size) {
    Page<DormitoryAdmin> dormitoryAdminPage = new Page<>(page, size);
    Page<DormitoryAdmin> resultPage = dormitoryAdminMapper.selectPage(dormitoryAdminPage, null);
    PageVO pageVO = new PageVO();
    pageVO.setData(resultPage.getRecords());
    pageVO.setTotal(resultPage.getTotal());

    return pageVO;
}
Controller
@GetMapping("/list/{page}/{size}")
public ResultVo list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
    return ResultVOUtil.success(dormitoryAdminService.list(page, size));
}
3.3 搜索功能
① 前端
 page(currentPage){
    const _this = this
    if(_this.ruleForm.value == ''){
        axios.get('http://localhost:8081/dormitoryAdmin/list/'+currentPage+'/'+_this.pageSize).then(function (resp) {
            _this.tableData = resp.data.data.data
            _this.total = resp.data.data.total
        })
    } else {
        _this.ruleForm.page = _this.currentPage
        axios.get('http://localhost:8081/dormitoryAdmin/search',{params:_this.ruleForm}).then(function (resp) {
            _this.tableData = resp.data.data.data
            _this.total = resp.data.data.total
        })
    }
},
搜索框为空时
● 不能用list的请求, 要用search的请求
● 否则查询的数据不是搜索的数据, 是没有经过模糊搜索的数据

submitForm(formName) {
    const _this = this
    //让翻页复原
    _this.currentPage = 1
    this.$refs[formName].validate((valid) => {
        if (valid) {
            const _this = this
            _this.ruleForm.page = _this.currentPage
            axios.get('http://localhost:8081/dormitoryAdmin/search',{params:_this.ruleForm}).then(function (resp) {
                _this.tableData = resp.data.data.data
                _this.total = resp.data.data.total
            })
        }
    });
},
按下搜索提交表单时, 将当前页复原, 设置为第一页
否则在第2页按下搜索时, 搜索完后的结果也会在第2页
② 后端
Data
@Data
public class SearchForm {
    private String key;
    private String value;
    private Integer page;
    private Integer size;
}
Service
 @Override
public PageVO search(SearchForm searchForm) {
    Page<DormitoryAdmin> dormitoryAdminPage = new Page<>(searchForm.getPage(), searchForm.getSize());
    Page<DormitoryAdmin> resultPage = null;

    if(searchForm.getValue().equals("")) { //搜索为空
        resultPage = dormitoryAdminMapper.selectPage(dormitoryAdminPage, null);
    } else {
        QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(searchForm.getKey(), searchForm.getValue());
        resultPage = dormitoryAdminMapper.selectPage(dormitoryAdminPage, queryWrapper);
    }

    PageVO pageVo = new PageVO();
    pageVo.setData(resultPage.getRecords());
    pageVo.setTotal(resultPage.getTotal());

    return pageVo;
}
Controller
@GetMapping("/search")
public ResultVo search(SearchForm searchForm) {
    return ResultVOUtil.success(dormitoryAdminService.search(searchForm));
}
③ 结果

3.4 修改宿管
① 前端
<template>
    <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;" >
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="ID">
                <el-input v-model="ruleForm.id" readonly></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="ruleForm.password"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <div style="width: 170px;height: 30px;">
                    <template>
                        <el-radio v-model="ruleForm.gender" label="男">男</el-radio>
                        <el-radio v-model="ruleForm.gender" label="女">女</el-radio>
                    </template>
                </div>
            </el-form-item>
            <el-form-item label="联系电话" prop="telephone">
                <el-input v-model.number="ruleForm.telephone"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                category:null,
                ruleForm: {
                    username: '',
                    password: '',
                    name: '',
                    gender: '男',
                    telephone: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ],
                    telephone: [
                        { required: true, message: '请输入联系电话', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8081/dormitoryAdmin/update',_this.ruleForm).then(function (resp) {
                            if(resp.data.code == 0){
                                _this.$alert(_this.ruleForm.username+'修改成功', '', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/dormitoryAdminManager')
                                    }
                                });
                            }
                        })
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8081/dormitoryAdmin/findById/'+_this.$route.query.id).then(function (resp) {
                _this.ruleForm = resp.data.data
            })
        }
    }
</script>




点击编辑后, vue路由跳转到修改界面

在修改界面创建时, 先发送请求得到id的宿管数据, 赋值给与视图绑定的数据

② 后端
Service
单表修改和查询, 用的都是IService中的方法
Controller
@GetMapping("/findById/{id}")
public ResultVo findById(@PathVariable Integer id) {
    return ResultVOUtil.success(dormitoryAdminService.getById(id));
}

@PutMapping("/update")
public ResultVo update(@RequestBody DormitoryAdmin dormitoryAdmin) {
    boolean update = dormitoryAdminService.updateById(dormitoryAdmin);
    if(update) {
        return ResultVOUtil.success(null);
    } else {
        return ResultVOUtil.fail();
    }
}
③ 结果




3.5 删除宿管
① 前端
 del(row) {
  const _this = this
  this.$confirm('确认删除【'+row.username+'】吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
  }).then(() => {
      axios.delete('http://localhost:8081/dormitoryAdmin/deleteById/'+row.id).then(function (resp) {
          if(resp.data.code==0){
              _this.$alert('【'+row.username+'】已删除', '', {
                  confirmButtonText: '确定',
                  callback: action => {
                      location.reload()
                  }
              });
          }
      });
  }).catch(() => {
      this.$message({
          type: 'info', 
          message: '已取消删除',
      })
  });
}
点击确定就发送请求

点击取消则是弹出一条消息

② 后端
Service
单表修改和查询, 用的都是IService中的方法
Controller
 @ApiOperation("删除宿管")
    @DeleteMapping("/deleteById/{id}")
    public ResultVo deleteById(@PathVariable Integer id) {
        if(dormitoryAdminService.removeById(id))
            return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }
③ 结果



4. 学生模块
4.1 添加学生
① 前端
<template>
    <div
            style="
      margin-top: 60px;
      margin-left: 330px;
      width: 300px;
      height: 500px;
      border: 0px solid red;
    "
    >
        <el-form
                :model="ruleForm"
                :rules="rules"
                ref="ruleForm"
                label-width="100px"
                class="demo-ruleForm"
        >
            <el-form-item label="学号" prop="number">
                <el-input v-model="ruleForm.number"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <div style="width: 170px; height: 30px">
                    <template>
                        <el-radio v-model="ruleForm.gender" label="男">男</el-radio>
                        <el-radio v-model="ruleForm.gender" label="女">女</el-radio>
                    </template>
                </div>
            </el-form-item>
            <el-form-item label="宿舍" prop="telephone">
                <el-select v-model="ruleForm.dormitoryId" placeholder="请选择宿舍">
                    <el-option
                            v-for="item in dormitoryList"
                            :label="item.name"
                            :value="item.id"
                            :key="item.id"
                    ></el-option>
                </el-select>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')"
                >立即创建
                </el-button
                >
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                category: null,
                ruleForm: {
                    number: "",
                    name: "",
                    gender: "",
                    dormitoryId: "",
                },
                dormitoryList: "",
                rules: {
                    number: [{required: true, message: "请输入学号", trigger: "blur"}],
                    name: [{required: true, message: "请输入姓名", trigger: "blur"}],
                    gender: [{required: true, message: "请输入性别", trigger: "blur"}],
                    dormitoryId: [
                        {required: true, message: "请选择宿舍", trigger: "change"},
                    ],
                },
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    axios.post('http://localhost:8081/student/save', this.ruleForm).then((response) => {
                      if(response.data.code === 0) {
                        this.$alert(this.ruleForm.name + '添加成功', '', {
                          confirmButtonText: '确定',
                          callback: action => {
                            this.$route.push('/studentManager')
                          }
                        })
                      }
                    })
                })
            },
          resetForm(formName) {
              this.$refs[formName].resetField();
          }
        },
      created() {
          axios.get('http://localhost:8081/dormitory/availableList').then((response) => {
            this.dormitoryList = response.data.data
          })
      }
    };
</script>

<style>
</style>
点击确定就发送请求

点击取消则是弹出一条消息

② 后端
Service
public Boolean mySave(Student student) {
    //添加学生
    student.setCreateDate(CommonUtil.createDate());
    int save = studentMapper.insert(student);
    if(save != 1)
        return false;

    //宿舍可住人员-1
    Dormitory dormitory = dormitoryMapper.selectById(student.getDormitoryId());
    if(dormitory.getAvailable() == 0)
        return false;

    dormitory.setAvailable(dormitory.getAvailable() - 1);
    int update = dormitoryMapper.updateById(dormitory);
    if(update != 1)
        return false;

    return true;
}
添加完学生后, 宿舍可住人数也要减一
Controller
@ApiOperation("添加学生")
@PostMapping("/save")
public ResultVo save(@RequestBody Student student) {
    if(studentService.mySave(student)) return ResultVOUtil.success(null);
    return ResultVOUtil.fail();
}
③ 结果


4.2 学生管理
① 前端
<template>
    <div style="margin-top: 60px;margin-left:80px;border: 0px solid red;">
        <el-form style="margin-left: -40px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
                 class="demo-ruleForm">
            <el-form-item label="字段：" prop="key">
                <el-select v-model="ruleForm.key" style="width: 160px;float: left" placeholder="请选择字段">
                    <el-option label="学号" value="number"></el-option>
                    <el-option label="姓名" value="name"></el-option>
                </el-select>
            </el-form-item>
            <div style="border: 0px solid red;width: 400px;height: 60px;position: relative;top: -64px;left: 270px">
                <el-form-item label="值：">
                    <el-input v-model="ruleForm.value" placeholder="请输入关键字" style="width: 160px;"></el-input>
                    <el-button type="primary" icon="el-icon-search" style="position: relative;left: 10px;"
                               @click="submitForm('ruleForm')">搜索
                    </el-button>
                </el-form-item>
            </div>
        </el-form>

        <el-table
                :data="tableData"
                border
                stripe
                style="width: 100%;position: relative;top:-30px">
            <el-table-column
                    fixed
                    prop="id"
                    label="ID"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="dormitoryName"
                    label="宿舍"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="number"
                    label="学号"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="gender"
                    label="性别"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="state"
                    label="状态"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="createDate"
                    label="入住时间"
                    width="140">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="edit(scope.row)">编辑
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="del(scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 20px;float: right"
                       background
                       layout="prev, pager, next"
                       :page-size="pageSize"
                       :total="total"
                       :current-page.sync="currentPage"
                       @current-change="page">
        </el-pagination>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                tableData: null,
                currentPage: 1,
                pageSize: 5,
                total: null,
                key: '',
                value: '',
                ruleForm: {
                    key: '',
                    value: '',
                    page: '',
                    size: 5
                },
                rules: {
                    key: [
                        {required: true, message: '请选择字段', trigger: 'change'}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                //让翻页复原
                this.currentPage = 1
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.ruleForm.page = _this.currentPage
                        axios.get('http://localhost:8081/student/search', {params: this.ruleForm}).then((resp) => {
                            this.tableData = resp.data.data.data
                            this.total = resp.data.data.total
                        })
                    }
                });

            },
            page(currentPage) {
                if (this.ruleForm.value === '') {
                    axios.get('http://localhost:8081/student/list/' + currentPage + '/' + this.pageSize).then((resp) => {
                        this.tableData = resp.data.data.data
                        this.total = resp.data.data.total
                    })
                } else {
                    this.ruleForm.page = this.currentPage
                    axios.get('http://localhost:8081/student/search', {params: this.ruleForm}).then((resp) => {
                        this.tableData = resp.data.data.data
                        this.total = resp.data.data.total
                    })
                }

            },
            edit(row) {
                this.$router.push('/studentUpdate?id=' + row.id)
            },
            del(row) {
                this.$confirm('确认删除【' + row.name + '】吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete('http://localhost:8081/student/deleteById/' + row.id).then((resp) => {
                        if (resp.data.code === 0) {
                            this.$alert('【' + row.name + '】已删除', '', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    location.reload()
                                }
                            });
                        }
                    });
                });
            }
        },
        created() {
            axios.get('http://localhost:8081/student/list/1/' + this.pageSize).then((resp) => {
                this.tableData = resp.data.data.data
                this.total = resp.data.data.total
            })
        }
    }
</script>
② 后端
PageVO
@Data
public class PageVO {
    private Object data;
    private Long total;
}
Service
@Override
public PageVO list(Integer page, Integer size) {
    Page<Student> studentPage = new Page<>(page, size);
    Page<Student> resultPage = studentMapper.selectPage(studentPage, null);
    //转换成VO
    List<Student> studentList = resultPage.getRecords();
    List<StudentVO> studentVOList = new ArrayList<>();
    for(Student student : studentList) {
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(student, studentVO);
        studentVO.setDormitoryName(dormitoryMapper.selectById(student.getDormitoryId()).getName());
        studentVOList.add(studentVO);
    }

    PageVO pageVO = new PageVO();
    pageVO.setData(studentVOList);
    pageVO.setTotal(resultPage.getTotal());
    return pageVO;
}
BeanUtils.copyProperties(student, studentVO);直接将相同属性(类型和属性名都一致)复制给后面的对象

Controller
@ApiOperation("分页查询学生信息")
@GetMapping("/list/{page}/{size}")
public ResultVo list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
    return ResultVOUtil.success(studentService.list(page, size));
}
③ 结果

4.3 搜索
① 前端
与4.2一致。
② 后端
Service
@Override
public PageVO search(SearchForm searchForm) {
    Page<Student> studentPage = new Page<>(searchForm.getPage(), searchForm.getSize());
    Page<Student> resultPage = null;
    if(searchForm.getValue().equals("")) {  //搜索框为空
        resultPage = studentMapper.selectPage(studentPage, null);
    } else {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(searchForm.getKey(), searchForm.getValue());
        resultPage = studentMapper.selectPage(studentPage, queryWrapper);
    }

    //转换成VO
    List<Student> studentList = resultPage.getRecords();
    List<StudentVO> studentVOList = new ArrayList<>();
    for(Student student : studentList) {
        StudentVO studentVO = new StudentVO();
        //复制相同属性
        BeanUtils.copyProperties(student, studentVO);
        studentVO.setDormitoryName(dormitoryMapper.selectById(student.getDormitoryId()).getName());
        studentVOList.add(studentVO);
    }

    PageVO pageVO = new PageVO();
    pageVO.setData(studentVOList);
    pageVO.setTotal(resultPage.getTotal());
    return pageVO;
}

Controller
 @GetMapping("/search")
public ResultVo search(SearchForm searchForm) {
    return ResultVOUtil.success(studentService.search(searchForm));
}

③ 结果

4.4 修改学生信息
① 前端
<template>
    <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;" >
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="ID">
                <el-input v-model="ruleForm.id" readonly></el-input>
            </el-form-item>
            <el-form-item label="学号" prop="number">
                <el-input v-model="ruleForm.number"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <div style="width: 170px;height: 30px;">
                    <template>
                        <el-radio v-model="ruleForm.gender" label="男">男</el-radio>
                        <el-radio v-model="ruleForm.gender" label="女">女</el-radio>
                    </template>
                </div>
            </el-form-item>
            <el-form-item label="宿舍：" prop="dormitoryId">
                <el-select v-model="ruleForm.dormitoryId" placeholder="请选择宿舍">
                    <el-option v-for="item in dormitoryList" :label="item.name" :value="item.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                category:null,
                ruleForm: {
                    number: '',
                    name: '',
                    gender: '男',
                    dormitoryId: ''
                },
                dormitoryList: '',
                rules: {
                    number: [
                        { required: true, message: '请输入学号', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ],
                    gender: [
                        { required: true, message: '请输入性别', trigger: 'blur' }
                    ],
                    dormitoryId: [
                        { required: true, message: '请选择宿舍', trigger: 'change' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8081/student/update',_this.ruleForm).then(function (resp) {
                            if(resp.data.code === 0){
                                _this.$alert(_this.ruleForm.name+'修改成功', '', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/studentManager')
                                    }
                                });
                            }
                        })
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8081/dormitory/availableList').then(function (resp) {
                _this.dormitoryList = resp.data.data
            })
            axios.get('http://localhost:8081/student/findById/'+_this.$route.query.id).then(function (resp) {
                _this.ruleForm = resp.data.data
            })
        }
    }
</script>

② 后端
Mapper
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.sleepwalker.mapper.StudentMapper">
  <update id="subAvailable" parameterType="Integer">
    update dormitory.dormitory set available = available - 1 where id = #{id}
  </update>

  <update id="addAvailable">
    update dormitory.dormitory set available = available + 1 where id = #{id}
  </update>
</mapper>

如果使用BaseMapper中的方法，更新一个宿舍需要调用两个语句，selectById拿到available和updateById更新available。

Service
@Override
public Boolean update(StudentForm studentForm) {
    //学生信息
    Student student = new Student();
    BeanUtils.copyProperties(studentForm, student);
    int update = studentMapper.updateById(student);
    if(update != 1)
        return false;

    //宿舍余量更改
    if(!studentForm.getDormitoryId().equals(studentForm.getOldDormitoryId())) {
        try {
            //原来宿舍余量+1
            studentMapper.addAvailable(studentForm.getDormitoryId());
            //新宿舍余量+1
            studentMapper.subAvailable(studentForm.getOldDormitoryId());
        } catch (Exception e) {
            return false;
        }
    }

    return true;
}
若学生更换宿舍，宿舍的可住余量也要更改。

StudentForm
package com.sleepwalker.form;
import lombok.Data;

@Data
public class StudentForm {
    private Integer id;
    private Integer number;
    private String name;
    private String gender;
    private Integer dormitoryId;
    private Integer oldDormitoryId;
    private String state = "入住";
    private String createDate;

}

Controller
 @GetMapping("/findById/{id}")
public ResultVo findById(@PathVariable("id") Integer id) {
    Student student = studentService.getById(id);
    StudentForm studentForm = new StudentForm();
    BeanUtils.copyProperties(student, studentForm);
    studentForm.setOldDormitoryId(student.getDormitoryId());

    return ResultVOUtil.success(studentForm);
}
在student的基础上，添加一个oldDormitoryId。
新的宿舍id会覆盖掉旧的宿舍id，表单上只有一宿舍id，所以添加一个宿舍id就不会被覆盖掉。
这个旧宿舍id不会用来显示在页面上，只是存储下来，更新学生信息的时候就有旧的宿舍id和新的宿舍id。这样就不需要在学生信息更新前，根据学生id查出旧的宿舍id。
@PutMapping("/update")
public ResultVo update(@RequestBody StudentForm studentForm) {
    if(studentService.update(studentForm))
        return ResultVOUtil.success(null);
    return ResultVOUtil.fail();
}
除了get请求，其他请求的参数都要加上@RequestBody。

pom.xml
 <resources>
    <resource>
        <directory>src/main/java</directory>
        <includes>
            <include>**/*.xml</include>
        </includes>
    </resource>
</resources>
因为Mapper映射文件没有放在resource包下，不能找到。在pom.xml中加上该代码就能识别非resource包下的映射文件。
③ 结果


修改后





问题 
1.  mybatis-plus generator生成不了目录
将路径改为绝对路径
lobalConfig.setOutputDir("D:\\Programme1\\Java_Code\\Project\\Dormitory\\SpringBootProject\\dormitoryms" + "/src/main/java");
2. idea中注解“@Data”没有起set、get作用
getter和setter提示要装Lombok插件

3. 非resource包下的Mapper映射文件找不到
 <resources>
    <resource>
        <directory>src/main/java</directory>
        <includes>
            <include>**/*.xml</include>
        </includes>
    </resource>
</resources>
因为Mapper映射文件没有放在resource包下，不能找到。在pom.xml中加上该代码就能识别非resource包下的映射文件。
