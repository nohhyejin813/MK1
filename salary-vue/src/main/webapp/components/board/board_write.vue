<template>
<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<div class="form-group">
						<label for="salesCompanyId">기업명</label>
						<input type="text" id="salesCompanyId" v-model="salesCompanyId" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="memberId">영업담당자명</label>
						<input type="text" id="memberId" v-model="memberId" class="form-control" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="salesType">영업활동</label>
						<input type="text" id="salesType" v-model="salesType" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="salesMemo">활동메모</label>
						<input type="text" id="salesMemo" v-model="salesMemo" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="proposalTarget">제안대상</label>
						<input type="text" id="proposalTarget" v-model="proposalTarget" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="salesMemodetail">상세내용</label>
						<textarea id="salesMemodetail" v-model="salesMemodetail" class="form-control" rows="10" style="resize:none"></textarea>
					</div>
					<div class="form-group">
						<label for="board_file">첨부 이미지</label>
						<input type="file" id="board_file" name="board_file" class="form-control" accept="image/*"/>
					</div>
					<div class="form-group">
						<div class="text-right">
							<button type="button" @click='check_input' class="btn btn-primary">작성하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>
</template>
<script>
	module.exports = {
		data : function(){
			return {
				salesCompanyId : '',
				memberId : this.$store.state.user_name,
				salesType : '',
				salesMemo : '',
				proposalTarget : '',
				salesMemodetail : ''
			}
		},
		methods : {
			check_input : function(){
					if(this.salesCompanyId.length == 0){
						alert("기업명 입력해주세요")
						$("#salesCompanyId").focus()
						return 
					}
					if(this.salesMemodetail.length == 0){
						alert("내용을 입력해주세요")
						$("#salesMemodetail").focus()
						return 
					}		
					
/////

				const url = "http://localhost:1020/history/write";
				axios.post(url,
					{
				        salesCompanyId: this.salesCompanyId,
				        memberId: this.$store.state.user_idx,
				        salesType: this.salesType,
				        salesMemo: this.salesMemo,
				        proposalTarget: this.proposalTarget,
				        salesMemoDetail: this.salesMemodetail
					},
					{
						headers:{
							"content-type": "application/json"
						}
					}
				)
				.then(res => {
					console.log(res)
					if (res.data.status) {
						alert('등록이 완료되었습니다.')
						//this.$router.push('/')							
					}					

				
				})
				.catch(e=>{
					//this.setInfo("실패11","", e.response.data.message)
					console.log(e.res.data.message)
				})

////
					
					
			}
		}
	}


</script>