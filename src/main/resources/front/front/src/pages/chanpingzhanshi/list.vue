<template>
<div>
	<div :style='{"padding":"0","margin":"0px auto","borderColor":"#e7e7e7","borderRadius":"0px","background":"url(http://codegen.caihongy.cn/20231012/4cb66b61fe1d4cb09b72cf0a4768c7be.jpg) repeat-x center center","borderWidth":"0px","width":"80%","borderStyle":"solid"}' class="breadcrumb-preview">
		<el-breadcrumb :separator="'|'" :style='{"width":"auto","padding":"10px 30px 10px 0","fontSize":"18px","lineHeight":"1","background":"#fff","display":"inline-block"}'>
			<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
			<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
		</el-breadcrumb>
	</div>
	<div v-if="centerType" :style='{"padding":"0","margin":"0px auto","borderColor":"#e7e7e7","borderRadius":"0px","background":"url(http://codegen.caihongy.cn/20231012/4cb66b61fe1d4cb09b72cf0a4768c7be.jpg) repeat-x center center","borderWidth":"0px","width":"80%","borderStyle":"solid"}'>
		<el-button size="mini" @click="backClick">返回</el-button>
	</div>
	<div class="list-preview" :style='{"padding":"30px 10% 0","margin":"0px auto","alignItems":"flex-start","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","position":"relative","justifyContent":"flex-end"}'>
		<div class="category-3" :style='{"border":"0px solid #eee","padding":"0","boxShadow":"0px 0px 0px #eee","margin":"30px 0 0 50px","flexWrap":"wrap","background":"#fff","display":"flex","width":"250px","height":"auto","order":"4"}'>
			<div class="item" :class="swiperIndex == '-1' ? 'active' : ''" @click="getList(1, '全部')" :plain="isPlain">
				<div :style='{"width":"auto","fontSize":"14px","color":"inherit","display":"inline-block"}'>全部</div>
			</div>
			<div class="item" :class="swiperIndex == index ? 'active' : ''" v-for="(item, index) in fenlei" :key="index" @click="getList(1, item[feileiColumn], 'btn' + index)" :ref="'btn' + index" plain>
				<img v-if="item.image" :style='{"width":"50px","margin":"0 5px 0 0","objectFit":"cover","display":"inline-block","height":"50px"}' :src="baseUrl + (item.image?item.image.split(',')[0]:'')">
				<div :style='{"width":"auto","fontSize":"14px","color":"inherit","display":"inline-block"}'>{{item[feileiColumn]}}</div>
			</div>
		</div>
    <el-form :inline="true" :model="formSearch" class="list-form-pv" :style='{"padding":"0 0 0px","margin":"0px 0 0","borderColor":"#ddd","alignItems":"center","display":"flex","clear":"both","flexWrap":"wrap","background":"#fff","borderWidth":"0 0 0px","width":"100%","borderStyle":"solid","height":"auto","order":"1"}'>
      <el-form-item :style='{"margin":"0 10px 0 0"}'>
	    <div class="lable" v-if="true" :style='{"width":"auto","padding":"0 0 0 10px","lineHeight":"42px","textAlign":"right","display":"inline-block"}'>产品标题：</div>
        <el-input v-model="formSearch.tiezibiaoti" placeholder="产品标题" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
      </el-form-item>
	  <el-button v-if=" true " :style='{"cursor":"pointer","border":"0","padding":"0px 15px","margin":"0px 10px 0 10px","color":"#fff","display":"inline-block","outline":"none","borderRadius":"0px","background":"#ff777f","width":"100px","fontSize":"14px","lineHeight":"36px","height":"36px"}' type="primary" @click="getList(1, curFenlei)"><i v-if="true" :style='{"color":"#fff","margin":"0 10px 0 0","fontSize":"14px"}' class="el-icon-search"></i>查询</el-button>
	  <el-button v-if="btnAuth('chanpingzhanshi','新增')" :style='{"cursor":"pointer","border":"0px solid #ddd","padding":"0px 15px","margin":"0px 10px 0 0","color":"#fff","display":"inline-block","outline":"none","borderRadius":"0px","background":"#c5c5c5","width":"80px","fontSize":"14px","lineHeight":"36px","height":"36px"}' type="primary" @click="add('/index/chanpingzhanshiAdd')"><i v-if="false" :style='{"color":"#fff","margin":"0 10px 0 0","fontSize":"14px"}' class="el-icon-circle-plus-outline"></i>添加</el-button>
    </el-form>
	<div class="select2" :style='{"width":"100%","padding":"0","margin":"10px 0 0","background":"#fff","height":"auto","order":"2"}'>
	  <div :style='{"padding":"0px","margin":"4px 0","borderColor":"#eee","background":"none","borderWidth":"0 0 1px","width":"100%","position":"relative","borderStyle":"dashed","height":"auto"}' class="list" v-for="(item,index) in selectOptionsList" :key="item">
	    <div :style='{"padding":"0 5px","color":"#555","textAlign":"right","display":"inline-block","width":"auto","lineHeight":"32px","fontSize":"14px"}' class="label">{{item.name}}：</div>
	    <div :style='{"width":"auto","display":"inline-block","height":"auto"}' class="item-body">
	      <div class="item" @click="selectClick2(item,-1)" :class="item.check ==-1 ? 'active' : ''">全部</div>
	      <div class="item" @click="selectClick2(item,index1)" :class="item.check == index1 ? 'active' : ''" v-for="item1,index1 in item.list" :key="item1">{{item1}}</div>
	    </div>
	  </div>
	</div>
	<div class="sort_view" :style='{"padding":"0px 24px","margin":"30px 0 0","borderColor":"#e7e7e7","overflow":"hidden","textAlign":"right","background":"none","borderWidth":"0px 0 0","width":"calc(100% - 0px)","borderStyle":"solid","order":"2"}'>
	</div>
	<div class="list" :style='{"border":"0px solid #eee","padding":"0","margin":"30px 0 0","background":"#fff","flex":"1","width":"calc(100% - 300px)","order":"3"}'>
		
		<!-- 样式三 -->
		<div class="list3 index-pv1" :style='{"padding":"0","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","justifyContent":"space-between","height":"auto"}'>
		  <div v-for="(item, index) in dataList" :key="index" @click.stop="toDetail(item)" class="list-item animation-box">
		    <div :style='{"border":"0px solid #f6f6f6","width":"100%","padding":"0px","margin":"0 0px 0 0","overflow":"hidden","height":"320px"}' class="img-box">
				<img @click.stop="imgPreView(item.tiezifengmian)" :style='{"width":"100%","objectFit":"cover","display":"block","height":"100%"}' v-if="item.tiezifengmian && item.tiezifengmian.substr(0,4)=='http'" :src="item.tiezifengmian.split(',')[0]" class="image" />
				<img @click.stop="imgPreView(baseUrl + (item.tiezifengmian?item.tiezifengmian.split(',')[0]:''))" :style='{"width":"100%","objectFit":"cover","display":"block","height":"100%"}' v-else :src="baseUrl + (item.tiezifengmian?item.tiezifengmian.split(',')[0]:'')" class="image" />
			</div>
		    <div :style='{"width":"100%","padding":"0 0px","overflow":"hidden","alignItems":"center","display":"flex","height":"auto"}' class="item-info">
		      <div :style='{"width":"100%","padding":"0 0px 0 0","flex":"1"}'>
				<div class="title">{{item.tiezibiaoti}}</div>
				<div class="title">{{item.chanpingleibie}}</div>
				<div v-if="item.price" class="price"><span :style='{"fontSize":"12px"}'>￥</span>{{item.price}}</div>
		        <div :style='{"padding":"2px 0px","borderColor":"#f0f0f0","borderStyle":"dashed","float":"right","borderWidth":"0 0 0px 0","display":"none"}' class="time">
		          <span class="icon iconfont icon-shijian21"></span>
		          <span class="text">{{item.addtime}}</span>
		        </div>
		        <!-- <div :style='{"padding":"2px 0px","borderColor":"#f0f0f0","margin":"0 10px 0 0","borderStyle":"dashed","borderWidth":"0 0 0px 0","display":"inline-block"}' class="publisher">
		          <span class="icon iconfont icon-geren16"></span>
		          <span class="text">{{item.yonghuzhanghao}}</span>
		        </div> -->
		        <div :style='{"padding":"2px 0px","borderColor":"#eee","margin":"0 10px 0 0","borderStyle":"dashed","borderWidth":"0 0 0px 0","display":"inline-block"}' class="like">
		          <span class="icon iconfont icon-zan10"></span>
		          <span class="text">{{item.thumbsupnum}}</span>
		        </div>
		        <div :style='{"padding":"2px 0px","borderColor":"#eee","margin":"0 10px 0 0","borderStyle":"dashed","borderWidth":"0 0 0px 0","display":"inline-block"}' class="collect">
		          <span class="icon iconfont icon-shoucang10"></span>
		          <span class="text">{{item.storeupnum}}</span>
		        </div>
		      </div>
		      <div class="desc" v-html="item.tieziwenan"></div>
		    </div>
		  </div>
		</div>
	</div>

	
	<el-pagination
	  background
	  id="pagination"
	  class="pagination"
	  :pager-count="7"
	  :page-size="pageSize"
	  prev-text="<"
	  next-text=">"
	  :hide-on-single-page="false"
	  :layout='["total","prev","pager","next"].join()'
	  :total="total"
	  :style='{"padding":"5px 10%","margin":"20px auto","color":"#333","textAlign":"left","width":"100%","clear":"both","lineHeight":"40px","fontWeight":"500","height":"40px","order":"50"}'
	  @current-change="curChange"
      @size-change="sizeChange"
	  @prev-click="prevClick"
	  @next-click="nextClick"
	></el-pagination>

  </div>
  <el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
  	<img :src="previewImg" alt="" style="width: 100%;">
  </el-dialog>
</div>
</template>

<script>
  export default {
    //数据集合
    data() {
      return {
		selectIndex2: 0,
		selectOptionsList: [],
	    layouts: '',
		swiperIndex: -1,
        baseUrl: '',
        breadcrumbItem: [
          {
            name: '产品展示'
          }
        ],
        formSearch: {
          tiezibiaoti: '',
        },
        fenlei: [],
		feileiColumn: '',
        dataList: [],
        total: 1,
        pageSize: 8,
        totalPage: 1,
        curFenlei: '全部',
        isPlain: false,
        indexQueryCondition: '',
        timeRange: [],
		centerType:false,
		previewImg: '',
		previewVisible: false,
		sortType: 'id',
		sortOrder: 'desc',
      }
    },
    async created() {
		if(this.$route.query.centerType){
			this.centerType = true
		}
		this.baseUrl = this.$config.baseUrl;
      this.getFenlei();
      this.getList(1, '全部');
    },
    //方法集合
    methods: {
		selectClick2(row,index) {
			row.check = index
			if(index == -1){
				this.formSearch[row.tableName] = ''
			}else {
				this.formSearch[row.tableName] = row.list[index]
			}
			this.getList()
		},
		add(path) {
			let query = {}
			if(this.centerType){
				query.centerType = 1
			}
			this.$router.push({path: path,query:query});
		},
      getFenlei() {
		this.$http.get('chanpingleibie/list',{}).then(res => {
		  if (res.data.code == 0) {
		    this.fenlei = res.data.data.list
		  }
		});
		this.feileiColumn = 'chanpingleibie'
      },
      getList(page, fenlei, ref = '') {
		if(fenlei == '全部') this.swiperIndex = -1;
		for(let i=0;i<this.fenlei.length;i++) {
			if(fenlei == this.fenlei[i][this.feileiColumn]) {
				this.swiperIndex = i;
				break;
			}
		}
		if(fenlei){
			this.curFenlei = fenlei;
		}
        // if (this.curFenlei == '全部') {
        //   this.isPlain = false;
        // } else {
        //   this.isPlain = true;
        // }
        let params = {page, limit: this.pageSize};
        let searchWhere = {};
        if (this.formSearch.tiezibiaoti != '') searchWhere.tiezibiaoti = '%' + this.formSearch.tiezibiaoti + '%';
        if (this.curFenlei != '全部') searchWhere.chanpingleibie = this.curFenlei;
			let user = JSON.parse(localStorage.getItem('sessionForm'))
		if (this.sortType) searchWhere.sort = this.sortType
		if (this.sortOrder) searchWhere.order = this.sortOrder
        this.$http.get(`chanpingzhanshi/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(res => {
          if (res.data.code == 0) {
            this.dataList = res.data.data.list;
            this.total = Number(res.data.data.total);
            this.pageSize = Number(res.data.data.pageSize);
            this.totalPage = res.data.data.totalPage;
          }
        });
      },
	  sortClick(type){
		  if(this.sortType==type){
			  if(this.sortOrder == 'desc'){
				  this.sortOrder = 'asc'
			  }else{
				  this.sortOrder = 'desc'
			  }
		  }else{
			  this.sortType = type
			  this.sortOrder = 'desc'
		  }
		  this.getList(1, '全部')
	  },
      curChange(page) {
        this.getList(page,this.curFenlei);
      },
      prevClick(page) {
        this.getList(page,this.curFenlei);
      },
      sizeChange(size){
        this.pageSize = size
        this.getList(1,this.curFenlei);
      },
      nextClick(page) {
        this.getList(page,this.curFenlei);
      },
	  imgPreView(url){
		  this.previewImg = url
		  this.previewVisible = true
	  },
      toDetail(item) {
		  let params = {
			  id: item.id
		  }
		  if(this.centerType){
			  params.centerType = 1
		  }
        this.$router.push({path: '/index/chanpingzhanshiDetail', query: params});
      },
	btnAuth(tableName,key){
		if(this.centerType){
			return this.isBackAuth(tableName,key)
		}else{
			return this.isAuth(tableName,key)
		}
	},
	backClick() {
		this.$router.push({path: '/index/center'});
	},
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview .list-form-pv .el-input {
		width: auto;
	}

	.breadcrumb-preview .el-breadcrumb /deep/ .el-breadcrumb__separator {
		margin: 0 9px;
		color: #ccc;
		font-weight: 500;
	}
	
	.breadcrumb-preview .el-breadcrumb .item1 /deep/ .el-breadcrumb__inner a {
		color: #333;
		display: inline-block;
	}
	
	.breadcrumb-preview .el-breadcrumb .item2 /deep/ .el-breadcrumb__inner a {
		color: #666;
		display: inline-block;
	}
	
	.category-1 .item {
		cursor: pointer;
		padding: 0 20px;
		margin: 0 10px;
		color: #666;
		display: inline-block;
		font-size: 14px;
		border-color: #e9e9e9;
		line-height: 50px;
		border-radius: 0px;
		background: none;
		width: calc(100% - 20px);
		border-width: 0 0 1px;
		border-style: solid;
		text-align: center;
	}
	
	.category-1 .item:hover {
		cursor: pointer;
		margin: 0 10px;
		color: #666;
		background: none;
		width: calc(100% - 20px);
		line-height: 50px;
		text-align: center;
	}
	
	.category-1 .item.active {
		border: 0px solid #e70012;
		border-radius: 0px;
		margin: 0 0 0px;
		color: #fff;
		background: url(http://codegen.caihongy.cn/20231013/9c22fb3231794db8964f7a2102d7dcda.jpg) no-repeat center top;
		width: 100%;
		font-size: 16px;
		line-height: 50px;
	}
	
	.category-2 .item {
		cursor: pointer;
		border-radius: 4px;
		margin: 0 0 10px 0;
		color: #999;
		background: #efefef;
		width: 100%;
		font-size: 14px;
		line-height: 36px;
		text-align: center;
	}
	
	.category-2 .item:hover {
		cursor: pointer;
		border-radius: 4px;
		margin: 0 0 10px 0;
		color: #999;
		background: #efefef;
		width: 100%;
		font-size: 14px;
		line-height: 36px;
		text-align: center;
	}
	
	.category-2 .item.active {
		cursor: pointer;
		border-radius: 4px;
		margin: 0 0 10px 0;
		color: #999;
		background: #efefef;
		width: 100%;
		font-size: 14px;
		line-height: 36px;
		text-align: center;
	}
	
	.category-3 .item {
		cursor: pointer;
		padding: 8px 20px;
		margin: 0 0 20px;
		color: #666;
		display: flex;
		border-color: #e9e9e9;
		line-height: 50px;
		flex-wrap: wrap;
		border-radius: 0px;
		background: none;
		width: 100%;
		justify-content: center;
		border-width: 0 0 1px;
		align-items: center;
		border-style: solid;
		text-align: center;
		min-width: 100px;
	}
	
	.category-3 .item:hover {
		padding: 8px 20px;
		margin: 0 0 20px;
		color: #fff;
		background: url(http://codegen.caihongy.cn/20231013/9c22fb3231794db8964f7a2102d7dcda.jpg) no-repeat center top / auto 100%;
		width: 100%;
		line-height: 50px;
	}
	
	.category-3 .item.active {
		margin: 0 0 20px;
		color: #fff;
		background: url(http://codegen.caihongy.cn/20231013/9c22fb3231794db8964f7a2102d7dcda.jpg) no-repeat center top / auto 100%;
		width: 100%;
	}
	
	.list-form-pv .el-input /deep/ .el-input__inner {
		padding: 0 10px;
		margin: 0;
		color: #333;
		font-size: 14px;
		border-color: #eee;
		line-height: 36px;
		border-radius: 0px;
		outline: none;
		max-width: 160px;
		width: auto;
		border-width: 0 0 1px;
		border-style: solid;
		height: 36px;
	}
	
	.list-form-pv .el-select /deep/ .el-input__inner {
	}
	
	.list-form-pv .el-date-editor /deep/ .el-input__inner {
		padding: 0 30px;
		margin: 0;
		color: #333;
		display: inline-block;
		font-size: 14px;
		border-color: #eee;
		line-height: 36px;
		border-radius: 0px;
		outline: none;
		max-width: 160px;
		width: auto;
		border-width: 0 0 1px;
		border-style: solid;
		height: 36px;
	}
	
	.list .index-pv1 .animation-box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	
	.list .index-pv1 .animation-box:hover {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.3s;
		z-index: 1;
	}
	
	.list .index-pv1 .animation-box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
	}
	
	.list .index-pv1 .animation-box img:hover {
		transform: rotate(0deg) scale(1.05) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.3s;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .btn-prev {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: #f4f4f5;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		min-width: 35px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .btn-next {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: #f4f4f5;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		min-width: 35px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .btn-prev:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: #f4f4f5;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .btn-next:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: #f4f4f5;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0;
		display: inline-block;
		vertical-align: top;
	}
	
	#pagination.el-pagination /deep/ .el-pager .number {
		cursor: pointer;
		padding: 0 4px;
		margin: 0 5px;
		color: #666;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		border-radius: 2px;
		background: #f4f4f5;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pager .number:hover {
		cursor: pointer;
		padding: 0 4px;
		margin: 0 5px;
		color: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		border-radius: 2px;
		background: #ff3d00;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pager .number.active {
		cursor: default;
		padding: 0 4px;
		margin: 0 5px;
		color: #FFF;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		border-radius: 2px;
		background: #ff3d00;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 13px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 28px;
		text-align: center;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 14px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 14px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}

	.list3 .list-item {
				border: 0px solid #eee;
				cursor: pointer;
				padding: 10px;
				margin: 0 0 24px;
				background: #f8f8f8;
				display: flex;
				width: 49%;
				font-size: 0;
				position: relative;
				flex-wrap: wrap;
				height: auto;
			}
	
	.list3 .list-item:hover {
				border: 0px solid #b4ebcc;
				background: #b4ebcc;
			}
	
	.list3 .list-item .title {
				padding: 0 0px;
				overflow: hidden;
				color: #333;
				white-space: nowrap;
				background: none;
				width: 100%;
				font-size: 14px;
				border-color: #eee;
				border-width: 0 0 1px 0;
				line-height: 36px;
				text-overflow: ellipsis;
				border-style: solid;
			}
	
	.list3 .list-item:hover .title {
				color: #000;
				border-color: #d0fce3;
				border-width: 0 0 1px 0;
				border-style: solid;
			}
	.list3 .list-item .price {
				padding: 0 10px;
				color: #f00;
				font-size: 14px;
				border-color: #eee;
				border-width: 0 0 1px 0;
				line-height: 36px;
				border-style: solid;
			}
	
	.list3 .list-item:hover .price {
				color: #f00;
				border-color: #d0fce3;
				border-width: 0 0 1px 0;
				border-style: solid;
			}
	
	.list3 .list-item .time .icon {
				margin: 0 4px 0 0;
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .time .icon {
				color: #333;
			}
	.list3 .list-item .time .text {
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .time .text {
				color: #666;
			}
	
	.list3 .list-item .publisher .icon {
				margin: 0 2px 0 0;
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .publisher .icon {
				color: #666;
			}
	.list3 .list-item .publisher .text {
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .publisher .text {
				color: #666;
			}
	
	.list3 .list-item .like .icon {
				margin: 0 2px 0 0;
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .like .icon {
				color: #666;
			}
	.list3 .list-item .like .text {
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .like .text {
				color: #666;
			}
	
	.list3 .list-item .collect .icon {
				margin: 0 2px 0 0;
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .collect .icon {
				color: #666;
			}
	.list3 .list-item .collect .text {
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .collect .text {
				color: #666;
			}
	
	.list3 .list-item .view .icon {
				margin: 0 2px 0 0;
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .view .icon {
				color: #666;
			}
	.list3 .list-item .view .text {
				color: #999;
				font-size: 14px;
				line-height: 28px;
			}
	.list3 .list-item:hover .view .text {
				color: #666;
			}
	
	.list3 .list-item .desc {
				margin: 0 0 0 5%;
				overflow: hidden;
				color: #999;
				text-indent: 2em;
				display: none;
				width: 45%;
				font-size: 14px;
				line-height: 24px;
				align-items: center;
				height: 172px;
			}
	.list3 .list-item:hover .desc {
				color: #fff;
			}
	
	.select2 .list .item-body .item {
				padding: 0 5px;
				color: #666;
				display: inline-block;
				font-size: 14px;
				line-height: 32px;
			}
	.select2 .list .item-body .item:hover {
				color: #F5BB00;
				background: none;
			}
	.select2 .list .item-body .item.active {
				color: #F5BB00;
				background: none;
			}
</style>
