<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Default JSP Page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=devce-width, initial-scale=1.0">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" />
		<style>
			.card-registration .select-input.form-control[readonly]:not([disabled]) {
				font-size: 1rem;
				line-height: 2.15;
				padding-left: .75em;
				padding-right: .75em;
			}

			.card-registration .select-arrow {
				top: 13px;
			}
		</style>
	</head>

	<body>
		<header class="container-fluid">
		</header>
		<main class="container-fluid">
			<section class="h-100 bg-dark">
				<div class="container py-5 h-100">
					<div class="row d-flex justify-content-center align-items-center h-100">
						<div class="container-sm d-flex justify-content-center align-items-center">
							<div class="row w-75">
								<div class="card card-registration my-4">
									<form action="register" method="post" class="row g-0">
										<div class="col-xl-4 d-none d-xl-block">
											<img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
												alt="Sample photo" class="img-fluid"
												style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;" />
										</div>
										<div class="col-xl-8">
											<div class="card-body p-md-5 text-black">
												<h3 class="mb-5 text-uppercase">
												Đăng ký tài khoản bước ${STEP2 == null ? 1 : 2}
												</h3>
												<div class="row">
													<div class="form-outline">
														<label class="form-label" for="form3Example1n">
															${STEP2 == null ? 'Họ và tên' : 'Tên đăng nhập'}
														</label>
														<input type="text" name="${STEP2 == null ? 'fullname' : 'username'}" id="form3Example1n"
															class="form-control form-control-lg" value="${STEP2 == null ? REGISTER_USER.fullname : REGISTER_USER.username}" required/>
													</div>
												</div>
												<div class="row mt-3">
													<div class="form-outline">
														<label class="form-label" for="form3Example1n1">
															${STEP2 == null ? 'Số điện thoại' : 'Mật khẩu'}
														</label>
														<input type="${STEP2 == null ? 'text' : 'password'}" name="${STEP2 == null ? 'phoneNumber' : 'password'}" id="form3Example1n1"
															class="form-control form-control-lg" value="${STEP2 == null ? REGISTER_USER.phoneNumber : ''}" required/>
													</div>
												</div>
												<c:if var="test" test="${STEP2 == null}">
													<div class="row my-2">
														<div class="input-group mb-3">
															<label class="input-group-text" for="inputGroupSelect01">Vai trò</label>
															<select name="userRole" class="form-select" id="inputGroupSelect01">
																<option ${REGISTER_USER.userRole == true ? selected : ''} value="0">Người thuê trọ</option>
																<option ${REGISTER_USER.userRole == false ? selected : ''} value="1">Người cho thuê</option>
															</select>
														</div>
													</div>
													<div class="d-md-flex justify-content-start align-items-center mb-4 py-0">
														<h6 class="mb-0 me-4">Giới tính: </h6>
														<div class="form-check form-check-inline mb-0 me-4">
															<input class="form-check-input" type="radio"
																name="gender" id="femaleGender"
																value="1" ${REGISTER_USER.gender == true ? 'checked' : ''}/>
															<label class="form-check-label"
																for="femaleGender">Nam</label>
														</div>
														<div class="form-check form-check-inline mb-0 me-4">
															<input class="form-check-input" type="radio"
																name="gender" id="maleGender" value="0" ${REGISTER_USER.gender == false ? 'checked' : ''}/>
															<label class="form-check-label" for="maleGender">Nữ</label>
														</div>
													</div>
												</c:if>
												<c:if var="test" test="${STEP2 != null}">
													<div class="row mt-3">
														<div class="form-outline">
															<label class="form-label" for="form3Example1n1">
																Nhập lại Mật khẩu
															</label>
															<input type="password" name="confirmPassword" id="form3Example1n1"
																class="form-control form-control-lg" required/>
														</div>
													</div>
												</c:if>
												<div class="d-flex mt-2">
													<button type="submit" name="register" value="${STEP2 == null ? 'step1' : 'STEP2'}" class="col-9 btn btn-warning btn-lg">${STEP2 == null ? 'Tiếp tục' : 'Đăng ký'}</button>
													<button type="button" class="col-3 btn btn-light btn-lg">Đặt lại</button>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</main>
		<footer class="container-fluid">
		</footer>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
			crossorigin="anonymous"></script>
	</body>

</html>