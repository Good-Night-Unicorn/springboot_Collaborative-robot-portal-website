<template>
  <div>
    <div class="container" :style='{"minHeight":"100vh","padding":"20px  20px 20px 400px","alignItems":"center","background-attachment":"fixed","background":"url(http://codegen.caihongy.cn/20240220/b7c1c0cd08d847ecb5e7145e3cdc43ae.jpg) no-repeat  center top / 100% 100%,#f9fbfd","display":"flex","width":"100%","justifyContent":"center"}'>
      <el-form :style='{"padding":"40px 20px","margin":"0","borderRadius":"8px","flexWrap":"wrap","background":"rgba(106, 171, 156)","display":"flex","width":"450px"}'>
        <div v-if="true" :style='{"padding":"40px 0","margin":"0 auto 20px auto","borderColor":"#fff","color":"#fff","textAlign":"center","borderWidth":"0 0 1px 0","width":"80%","lineHeight":"44px","fontSize":"28px","borderStyle":"solid"}' class="title-container">管理员登录</div>
        <div v-if="loginType==1" class="list-item" :style='{"width":"80%","margin":"0 auto 10px","alignItems":"center","flexWrap":"wrap","display":"flex"}'>
          <div v-if="true" class="lable" :style='{"width":"100%","letterSpacing":"1px","lineHeight":"44px","fontSize":"14px","color":"#fff"}'>用户名：</div>
          <input :style='{"border":"0","padding":"0 10px","color":"#000000","borderRadius":"5px","width":"100%","fontSize":"14px","height":"44px"}' placeholder="请输入用户名" name="username" type="text" v-model="rulesForm.username">
        </div>
        <div v-if="loginType==1" class="list-item" :style='{"width":"80%","margin":"0 auto 10px","alignItems":"center","flexWrap":"wrap","display":"flex"}'>
          <div v-if="true" class="lable" :style='{"width":"100%","letterSpacing":"1px","lineHeight":"44px","fontSize":"14px","color":"#fff"}'>密码：</div>
          <input :style='{"border":"0","padding":"0 10px","color":"#000000","borderRadius":"5px","width":"100%","fontSize":"14px","height":"44px"}' placeholder="请输入密码" name="password" type="password" v-model="rulesForm.password">
        </div>

        <div :style='{"width":"80%","margin":"20px auto"}' v-if="roles.length>1" prop="loginInRole" class="list-type">
          <el-radio v-if="loginType==1||(loginType==2&&item.roleName!='管理员')" v-for="item in roles" v-bind:key="item.roleName" v-model="rulesForm.role" :label="item.roleName">{{item.roleName}}</el-radio>
        </div>

		
        <div :style='{"padding":"0 16px","margin":"0 auto","alignItems":"center","flexWrap":"wrap","display":"flex"}'>
          <el-button v-if="loginType==1" :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"10px 5px","outline":"none","color":"#212529","borderRadius":"4px","background":"#fff","width":"auto","letterSpacing":"1px","fontSize":"14px","height":"44px"}' type="primary" @click="login()" class="loginInBt">登录</el-button>
        </div>
      </el-form>

    </div>
  </div>
</template>
<script>
import menu from "@/utils/menu";
export default {
  data() {
    return {
		verifyCheck2: false,
		flag: false,
      baseUrl:this.$base.url,
      loginType: 1,
      rulesForm: {
        username: "",
        password: "",
        role: "",
      },
      menus: [],
      roles: [],
      tableName: "",
    };
  },
  mounted() {
    let menus = menu.list();
    this.menus = menus;

    for (let i = 0; i < this.menus.length; i++) {
      if (this.menus[i].hasBackLogin=='是') {
        this.roles.push(this.menus[i])
      }
    }

  },
  created() {

  },
  destroyed() {
	    },
  components: {
  },
  methods: {

    //注册
    register(tableName){
		this.$storage.set("loginTable", tableName);
		this.$router.push({path:'/register',query:{pageFlag:'register'}})
    },
    // 登陆
    login() {

		if (!this.rulesForm.username) {
			this.$message.error("请输入用户名");
			return;
		}
		if (!this.rulesForm.password) {
			this.$message.error("请输入密码");
			return;
		}
		if(this.roles.length>1) {
			if (!this.rulesForm.role) {
				this.$message.error("请选择角色");
				return;
			}

			let menus = this.menus;
			for (let i = 0; i < menus.length; i++) {
				if (menus[i].roleName == this.rulesForm.role) {
					this.tableName = menus[i].tableName;
				}
			}
		} else {
			this.tableName = this.roles[0].tableName;
			this.rulesForm.role = this.roles[0].roleName;
		}
		
		this.loginPost()
    },
	loginPost() {
		this.$http({
			url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
			method: "post"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.$storage.set("Token", data.token);
				this.$storage.set("role", this.rulesForm.role);
				this.$storage.set("sessionTable", this.tableName);
				this.$storage.set("adminName", this.rulesForm.username);
				this.$router.replace({ path: "/" });
			} else {
				this.$message.error(data.msg);
			}
		});
	},
  }
}
</script>

<style lang="scss" scoped>
.container {
  min-height: 100vh;
  position: relative;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
      background: url(http://codegen.caihongy.cn/20240220/b7c1c0cd08d847ecb5e7145e3cdc43ae.jpg) no-repeat  center top / 100% 100%,#f9fbfd;
        
  .list-item /deep/ .el-input .el-input__inner {
		border: 0;
		border-radius: 5px;
		padding: 0 10px;
		color: #000000;
		width: 100%;
		font-size: 14px;
		height: 44px;
	  }
  
  .list-item.select /deep/ .el-select .el-input__inner {
		border: 1px solid rgba(64, 158, 255, 1);
		padding: 0 10px;
		box-shadow: 0 0 6px rgba(64, 158, 255, .5);
		outline: 1px solid #efefef;
		color: rgba(64, 158, 255, 1);
		width: 288px;
		font-size: 14px;
		outline-offset: 4px;
		height: 44px;
	  }
  
  .list-code /deep/ .el-input .el-input__inner {
  	  	border: 0;
  	  	border-radius: 5px;
  	  	padding: 0 10px;
  	  	color: #000000;
  	  	width: 100%;
  	  	font-size: 14px;
  	  	height: 44px;
  	  }

  .list-type /deep/ .el-radio__input .el-radio__inner {
		background: rgba(53, 53, 53, 0);
		border-color: #fff;
	  }
  .list-type /deep/ .el-radio__input.is-checked .el-radio__inner {
        background: #000000;
        border-color: #000000;
      }
  .list-type /deep/ .el-radio__label {
		color: #fff;
		font-size: 14px;
	  }
  .list-type /deep/ .el-radio__input.is-checked+.el-radio__label {
        color: #000000;
        font-size: 14px;
      }
}

</style>
