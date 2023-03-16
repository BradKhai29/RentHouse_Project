<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${USER != null}">
	<jsp:forward page="${root}"></jsp:forward>
</c:if>
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
									<form action="user" method="post" class="row g-0">
										<div class="col-xl-4 d-none d-xl-block">
											<img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
												alt="Sample photo" class="img-fluid"
												style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;" />
										</div>
										<div class="col-xl-8">
											<div class="card-body p-md-5 text-black">
												<h3 class="mb-5 text-uppercase">
												    Đăng Nhập
												</h3>
												<div class="row">
													<div class="form-outline">
														<label class="form-label" for="form3Example1n">
															Tên đăng nhập
														</label>
														<input type="text" name="username" id="form3Example1n"
															class="form-control form-control-lg" required/>
													</div>
												</div>
												<div class="row mt-3">
													<div class="form-outline">
														<label class="form-label" for="form3Example1n1">
															Mật khẩu
														</label>
														<input type="password" name="password" id="form3Example1n1"
															class="form-control form-control-lg" required/>
													</div>
												</div>
                                                <div class="row mt-1">
                                                    <div class="form-outline">
                                                        <input class="form-check-input" type="checkbox" name="remember" value="remember" id="flexCheckDefault">
                                                        <label class="form-check-label" for="flexCheckDefault">
                                                            Ghi nhớ đăng nhập
                                                        </label>
                                                    </div>
                                                </div>
												<div class="d-grid mt-2">
													<button type="submit" name="command" value="login" class="btn btn-warning btn-lg">
                                                        Đăng nhập
                                                    </button>
                                                    <a class="btn btn-link btn-lg" href="user?command=register">
                                                        Chưa có tài khoản, đăng kí ngay
                                                    </a>
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