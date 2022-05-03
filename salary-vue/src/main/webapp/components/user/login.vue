<template>
<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<div class="alert alert-danger" v-if='is_login_fail == true'>
						<h3>로그인 실패</h3>
						<p>아이디 비밀번호를 확인해주세요</p>
					</div>
					<form action="index.html" method="post" onsubmit="return check_input()">
						<div class="form-group">
							<label for="user_id">아이디</label>
							<input type="text" id="user_id" v-model="user_id" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="user_pw">비밀번호</label>
							<input type="password" id="user_pw" v-model="user_pw" class="form-control"/>
						</div>
						<div class="form-group text-right">
							<button type="button" @click='check_input' class="btn btn-primary">로그인</button>
							<router-link to='/join' class="btn btn-danger">회원가입</router-link>
						</div>
					</form>
				</div>
				
<button type="button" id='getInfo' @click='getInfo' class="btn btn-primary">정보확인</button>
사용자정보 : {{message}}
<table>
	<tr>
		<th>상태</th>
		<td id="status">{{status}}</td>
	</tr>		
	<tr>
		<th>토큰</th>
		<td id="token">{{token}}</td>
	</tr>
	<tr>
		<th>정보</th>
		<td id="info">{{info}}</td>
	</tr>						
</table>
		
						
			</div>
		</div>
		<div class="col-sm-3"></div>

	</div>
</div>
</template>
<script>
	const storage = window.sessionStorage;

	module.exports = {
		data : function(){
			return {
				is_login_fail : false,
				user_id : '',
				user_pw : '',
				status : '',
				info : '',
				message : '',
				token : ''
			}
		},
		methods : {
			setInfo(status, token,info){
				this.status=status
				this.token=token
				this.info=info
			},
			getInfo() {
				const url = "http://localhost:1020/user/info";
				axios.post(url,
					{
				        id: this.user_id,
				        password: this.user_pw		
					},
					{
						headers:{
							"jwt-auth-token":storage.getItem("jwt-auth-token")
						}
					}
				)
				.then(res => {
					this.setInfo(
						"정보 조회 성공",
						res.headers.auth_token,
						JSON.stringify(res.data)
					)
				
				})
				.catch(e=>{
					this.setInfo("실패11","", e.response.data.msg)
				})
				
				
			}, //[E]getInfo
			check_input : function(){
				// 유효성 검사
				if(this.user_id.length < 4){
					alert("아이디는 4글자 이상입니다")
					this.user_id = ''
					$("#user_id").focus()
					return 
				}
				if(this.user_pw.length < 4){
					alert("비밀번호는 4글자 이상입니다")
					this.user_pw = ''
					$("#user_pw").focus()
					return 
				}
				
				var params = new URLSearchParams()
				params.append('id',this.user_id)
				params.append('password',this.user_pw)				
				
				// [S]login 
				storage.setItem("jwt-auth-token", "")
				storage.setItem("login_user", "")


			    const url = "http://localhost:1020/user/login";
			    const sendParam = {
			        id: this.user_id,
			        password: this.user_pw
			    }
		
		    	axios.post(url, sendParam)
		        .then((res) => {
		            console.log(res)
		            
		            if (res.data.status) {
		            		alert('로그인되었습니다.')
		            	
		            		// 성공
		            		this.setInfo(
		            			"성공",
		            			res.headers["jwt-auth-token"],
		            			JSON.stringify(res.data.data)
		            		)
		            		storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"])
		            		storage.setItem("login_user",res.data.data.email);
		            		
							this.message = res.data.data.email + "로 로그인 되었습니다.";
							this.$store.state.user_login_chk = true
							this.$store.state.user_id = res.data.data.id
							this.$store.state.user_name = res.data.data.name
							this.$store.state.user_idx = res.data.data.memberId
							
							// 세션스토리지에도 저장한다(새로고침대비)
							sessionStorage.user_login_chk = true
							sessionStorage.user_id = res.data.data.id
							sessionStorage.user_name = res.data.data.name
							sessionStorage.user_idx = res.data.data.memberId 	
							
							//console.log(":::::::::::::::::::::::::")
							//console.log(storage.getItem("jwt-auth-token"))
							//console.log(storage.getItem("login_user"))
							
							//this.$router.push('/user_modify')
							//this.$router.push('/')
							
							
		            } else {
		            	
		            	this.setInfo("","","")
		            	this.message = "로그인해주세요."
		            	alert('입력정보를 확인하세요.')
		            }
		            
		            		            
		        })
		        .catch((error) => {
		        	this.setInfo("실패","", JSON.stringify(e.response || e.message))
		            console.log(error.response)
		        })				

				
				// [E]login				
			}, // [E]check_input
			
			init(){
				if (storage.getItem("jwt-auth-token")) {
					this.message=storage.getItem("login_user") + "로 로그인 되었습니다."
				} else {
					storage.setItem("jwt-auth-token", "")
				}
			} // [E]init()
		},
		mounted() {
			this.init();
		}
	}
</script>
