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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
        <link rel="stylesheet" href="${root}/assets/blog.css">
        <link rel="stylesheet" href="${root}/assets/style.css">
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
		<header class="container position-relative">
            <section class="blog-header py-3">
                <div class="row flex-nowrap justify-content-between align-items-center">
                    <div class="col-4 pt-1">
						<c:if test="${LOGIN_ERROR != null}">
							<div class="error alert alert-danger alert-dismissible fade show position-absolute top-0 start-50 translate-middle-x" role="alert">
								<strong>${LOGIN_ERROR}</strong>
								<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
							</div>
						</c:if>
                    </div>
                    <div class="col-4 text-center">
                        <a class="blog-header-logo text-dark text-uppercase" href="${root}">
                            Renty
                        </a>
                    </div>
                    <div class="col-4 d-flex justify-content-end align-items-center">
                        <a class="link-secondary" href="#" aria-label="Search" onclick="popUpSearchBar()">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="mx-3" role="img" viewBox="0 0 24 24"><title>Search</title><circle cx="10.5" cy="10.5" r="7.5"/><path d="M21 21l-5.2-5.2"/></svg>
                        </a>
                        <c:if test="${USER == null}">
                            <a class="btn mybtn ms-3" href="${user}?action=register">Đăng ký</a>
                        </c:if>
                    </div>
                </div>
            </section>
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
													<button type="submit" name="action" value="login" class="btn btn-warning btn-lg">
                                                        Đăng nhập
                                                    </button>
                                                    <a class="btn btn-link btn-lg" href="user?action=register">
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
		<footer class="text-center text-lg-start text-light bg-dark">
            <section class="">
                <div class="container text-center text-md-start mt-5">
                    <!-- Grid row -->
                    <div class="row mt-3 pt-3">
                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                            <!-- Content -->
                            <h6 class="text-uppercase fw-bold">Group name</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                Wibu Shogun
                            </p>
                        </div>
                        <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">

                        </div>
                        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">

                        </div>
                        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4 pb-5">
                            <h6 class="text-uppercase fw-bold">Contact</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p><i class="fas fa-envelope mr-3"></i> ledinhdangkhoa10a9@gmail.com</p>
                            <p><i class="fas fa-phone mr-3"></i> 0706042250</p>
                        </div>
                    </div>
                </div>
            </section>
        </footer>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
			crossorigin="anonymous"></script>
		<script>
			var error = document.querySelector(".error");
			const myTimeout = setTimeout(turnOff, 3000);

			function turnOff() {
				error.style.display = "none";
			}
		</script>
	</body>

</html>