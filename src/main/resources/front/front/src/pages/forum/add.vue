<template>
  <div :style='{"width":"100%","padding":"30px 7% 40px","margin":"0px auto","position":"relative","background":"#fff"}'>
    <div class="section-title" :style='{"margin":"10px 0","color":"#fff","textAlign":"center","background":"#333","width":"100%","fontSize":"20px","lineHeight":"54px"}'>交流中心</div>
	<div :style='{"padding":"20px 7%","margin":"0px auto","borderColor":"#ddd","borderRadius":"0px","background":"none","borderWidth":"0 0 1px","width":"100%","borderStyle":"solid"}'>
		<el-button size="mini" @click="backClick">返回</el-button>
	</div>
    <el-form class="add-update-preview" :model="form" :rules="rules" ref="form" label-width="180px">
      <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="标题" prop="title">
        <el-input v-model="form.title" placeholder="请输入标题"></el-input>
      </el-form-item>
      <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="类型" prop="isdone">
        <el-radio-group v-model="form.isdone">
          <el-radio label="开放">公开</el-radio>
          <el-radio label="关闭">私人</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item :style='{"padding":"10px","margin":"0 0 10px","background":"none"}' label="内容" prop="content">
        <editor
            :style='{"minHeight":"200px","border":"0","outline":"none","color":"#333","borderRadius":"4px","width":"100%","lineHeight":"32px","fontSize":"14px"}'
            v-model="form.content" 
            class="editor" 
            action="file/upload">
        </editor>
      </el-form-item>
      <el-form-item :style='{"padding":"0","margin":"0"}'>
        <el-button :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"4px","background":"#F5BB00","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' type="primary" @click="submitForm('form')">{{this.isEdit ? '修改' : '发布帖子'}} </el-button>
        <el-button :style='{"border":"0px solid rgba(64, 158, 255, 1)","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"#fff","borderRadius":"4px","background":"#333","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    //数据集合
    data() {
      return {
        form: {
          title: '',
          isdone: '开放',
          content: '',
          parentid: 0,
          userid: localStorage.getItem('frontUserid'),
          username: localStorage.getItem('username'),
		  toptime: '1990-01-01 00:00:00'
        },
        editorOption: {
          modules: {
            toolbar: [
              ["bold", "italic", "underline", "strike"],
              ["blockquote", "code-block"],
              [{ header: 1 }, { header: 2 }],
              [{ list: "ordered" }, { list: "bullet" }],
              [{ script: "sub" }, { script: "super" }],
              [{ indent: "-1" }, { indent: "+1" }],
              [{ direction: "rtl" }],
              [{ size: ["small", false, "large", "huge"] }],
              [{ header: [1, 2, 3, 4, 5, 6, false] }],
              [{ color: [] }, { background: [] }],
              [{ font: [] }],
              [{ align: [] }],
              ["clean"],
              ["image", "video"]
            ]
          }
        },
        isEdit: false,
        rules: {
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      if (this.$route.query.id != undefined) {
        this.isEdit = true;
        this.$http.get('forum/detail/' + this.$route.query.id,{}).then(res=>{
			if(res.data.code==0){
				this.form = res.data.data
			}
		})
      }
    },
    //方法集合
    methods: {
		// 返回按钮
		backClick(){
			history.back()
		},
      onEditorReady(editor) {
        editor.root.setAttribute('data-placeholder', "请输入内容...");
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$http.post(`forum/${this.isEdit ? 'update' : 'add'}`, this.form).then(res => {
              if (res.data.code === 0) {
                this.$message({
                  message: `${this.isEdit ? '修改' : '发布'}成功`,
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    if (this.isEdit) {
                      this.$router.push('/index/myForumList');
                    } else {
                      this.$router.push('/index/forum');
                    }
                  }
                });
              } else {
                this.$message.error(res.data.msg);
              }
            });
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .add-update-preview .el-form-item /deep/ .el-form-item__label {
        padding: 0 10px 0 0;
        color: #666;
        font-weight: 500;
        width: 180px;
        font-size: 14px;
        line-height: 40px;
        text-align: right;
      }
  
  .add-update-preview .el-form-item /deep/ .el-form-item__content {
    margin-left: 180px;
  }
  
  .add-update-preview .el-input /deep/ .el-input__inner {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 0 12px;
        box-shadow: 0 0 0px rgba(64, 158, 255, .5);
        outline: none;
        color: #666;
        background: #fff;
        width: 400px;
        font-size: 14px;
        height: 40px;
      }
  
  .add-update-preview .el-select /deep/ .el-input__inner {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 0 10px;
        box-shadow: 0 0 0px rgba(64, 158, 255, .5);
        outline: none;
        color: #666;
        background: #fff;
        width: 200px;
        font-size: 14px;
        height: 40px;
      }
  
  .add-update-preview .el-date-editor /deep/ .el-input__inner {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 0 10px 0 30px;
        box-shadow: 0 0 0px rgba(64, 158, 255, .5);
        outline: none;
        color: #666;
        background: #fff;
        width: 200px;
        font-size: 14px;
        height: 40px;
      }
  
  .add-update-preview /deep/ .el-upload--picture-card {
  	background: transparent;
  	border: 0;
  	border-radius: 0;
  	width: auto;
  	height: auto;
  	line-height: initial;
  	vertical-align: middle;
  }
  
  .add-update-preview /deep/ .upload .upload-img {
        border: 1px solid #ddd;
        cursor: pointer;
        border-radius: 6px;
        color: #999;
        background: #fff;
        width: 200px;
        font-size: 32px;
        line-height: 100px;
        text-align: center;
        height: 100px;
      }
  
  .add-update-preview /deep/ .el-upload-list .el-upload-list__item {
        border: 1px solid #ddd;
        cursor: pointer;
        border-radius: 6px;
        color: #999;
        background: #fff;
        width: 200px;
        font-size: 32px;
        line-height: 100px;
        text-align: center;
        height: 100px;
      }
  
  .add-update-preview /deep/ .el-upload .el-icon-plus {
        border: 1px solid #ddd;
        cursor: pointer;
        border-radius: 6px;
        color: #999;
        background: #fff;
        width: 200px;
        font-size: 32px;
        line-height: 100px;
        text-align: center;
        height: 100px;
      }
  
  .add-update-preview .el-textarea /deep/ .el-textarea__inner {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 12px;
        box-shadow: 0 0 0px rgba(64, 158, 255, .5);
        outline: none;
        color: #666;
        background: #fff;
        width: 400px;
        font-size: 14px;
        height: 120px;
      }
</style>
