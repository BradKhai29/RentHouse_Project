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
                        <c:if test="${INVALID_PHONE_NUMBER != null}">
							<div class="error alert alert-danger alert-dismissible fade show position-absolute top-0 start-50 translate-middle-x" role="alert">
								<strong>${INVALID_PHONE_NUMBER}</strong>
								<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
							</div>
						</c:if>
                        <c:if test="${CONFIRM_PASSWORD_NOT_MATCH != null}">
							<div class="error alert alert-danger alert-dismissible fade show position-absolute top-0 start-50 translate-middle-x" role="alert">
								<strong>${CONFIRM_PASSWORD_NOT_MATCH}</strong>
								<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
							</div>
						</c:if>
                        <c:if test="${EXIST_USERNAME != null}">
							<div class="error alert alert-danger alert-dismissible fade show position-absolute top-0 start-50 translate-middle-x" role="alert">
								<strong>${EXIST_USERNAME}</strong>
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
                            <a class="btn btn-outline-danger mybtn-outline" aria-current="page" href="${user}">Đăng nhập</a>
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
                                    <form action="register" method="post" class="row g-0">
                                        <div class="col-xl-4 d-none d-xl-block">
                                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
                                                 alt="Sample photo" class="img-fluid"
                                                 style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;" />
                                        </div>
                                        <div class="col-xl-8">
                                            <div class="card-body p-md-5 text-black">
                                                <h3 class="mb-0 text-uppercase">
                                                    Đăng ký tài khoản bước ${STEP2 == null ? 1 : 2}
                                                </h3>
												<br><a class="mb-4" href="user">Đã có tài khoản, quay lại đăng nhập</a>
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
    </body>

</html>