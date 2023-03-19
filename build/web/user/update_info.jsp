<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<c:if test="${USER == null}">
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
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="${root}/assets/style.css">
    </head>
    <body>
        <header class="container">
            <section class="blog-header py-3">
                <div class="row flex-nowrap justify-content-between align-items-center">
                    <div class="col-4 pt-1">
                        <a class="link-secondary" href="#">Subscribe</a>
                    </div>
                    <div class="col-4 text-center">
                        <a class="blog-header-logo text-dark" href="${root}">
                            Renty
                        </a>
                    </div>
                    <div class="col-4 d-flex justify-content-end align-items-center">
                        <a class="link-secondary" href="#" aria-label="Search" onclick="popUpSearchBar()">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="mx-3" role="img" viewBox="0 0 24 24"><title>Search</title><circle cx="10.5" cy="10.5" r="7.5"/><path d="M21 21l-5.2-5.2"/></svg>
                        </a>
                        <c:if test="${USER == null}">
                            <a class="btn btn-outline-danger mybtn-outline" aria-current="page" href="${user_update}">Đăng nhập</a>
                            <a class="btn mybtn ms-3" href="${user_update}?action=register">Đăng ký</a>
                        </c:if>
                        <c:if test="${USER != null}">
                            <div class="dropdown">
                                <button class="btn mybtn dropdown-toggle ms-3" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa-solid fa-circle-user icon"></i> ${USER.username}
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><a class="dropdown-item" href="">Đăng bài</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="${user}?action=logout">Đăng xuất</a></li>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                </div>
            </section>
        </header>
        <main class="container-fluid">
            <div class="row">
                <section class="container-md">
                    <div class="container py-3">
                        <div class="row">
                            <div class="col">
                                <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                                    <ol class="breadcrumb mb-0 fw-bold">
                                        <li class="breadcrumb-item"><a href="${user_update}">Home</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                        <c:if var="messageCheck" test="${message != null && success == null}">
                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                <strong>${message}</strong>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <c:if var="messageCheck" test="${message != null && success != null}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <strong>${message}</strong>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <form class="row" action="${user_update}" method="post">
                            <div class="col-lg-4">
                                <div class="card mb-4">
                                    <div class="card-body text-center" action="${user_update}" method="post">
                                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                                             class="rounded-circle img-fluid" style="width: 150px;">
                                        <h5 class="my-3">${USER.fullname}</h5>
                                        <h6 class="my-3">Tên đăng nhập : ${USER.username}</h6>
                                        <div class="d-flex justify-content-center mb-2">
                                            <c:choose>
                                                <c:when test="${UPDATE_PROFILE_CHECKPOINT == null && UPDATE_PASSWORD_CHECKPOINT == null}">
                                                    <button type="submit" class="btn btn-success fw-bold" name="action" value="UPDATE">Chỉnh sửa thông tin</button>
                                                </c:when>
                                                <c:when test="${UPDATE_PROFILE_CHECKPOINT != null}">
                                                    <button type="submit" class="btn btn-warning fw-bold" name="action" value="CONFIRM_UPDATE">Cập nhật thông tin</button>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                                <c:if var="testPasswd" test="${UPDATE_PASSWORD_CHECKPOINT == null && UPDATE_PROFILE_CHECKPOINT == null}">
                                    <div class="card mb-4 mb-lg-0">
                                        <div class="card-body p-0">
                                            <ul class="list-group list-group-flush rounded-3">
                                                <button class="list-group-item d-flex justify-content-between align-items-center p-3 btnlin"
                                                        type="submit" formaction="${user_update}" name="action" value="UPDATE_PASSWORD">
                                                    <i class="fa-solid fa-unlock-keyhole fa-2x text-danger"></i>
                                                    <p class="mb-0 fw-bold">Cài đặt mật khẩu</p>
                                                </button>
                                            </ul>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-lg-8">
                                <c:if var="testPasswd" test="${UPDATE_PASSWORD_CHECKPOINT == null}">
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <div class="row">
                                                <c:if var="test" test="${UPDATE_PROFILE_CHECKPOINT == null}">
                                                    <div class="col-sm-3 fw-bold">
                                                        <p class="mb-0">Tên khách hàng</p>
                                                    </div>
                                                    <div class="col-sm-9">
                                                        <p class="text-muted mb-0">${USER.fullname}</p>
                                                    </div>
                                                </c:if>
                                                <c:if var="test" test="${UPDATE_PROFILE_CHECKPOINT != null}">
                                                    <div class="input-group m-0">
                                                        <label class="input-group-text col-sm-3 fw-bold" for="fullname">Tên khách hàng</label>
                                                        <input name="fullname" type="text" class="form-control col-sm-9" id="fullname" value="${USER.fullname}" required>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <c:if var="test" test="${UPDATE_PROFILE_CHECKPOINT == null}">
                                                    <div class="col-sm-3 fw-bold">
                                                        <p class="mb-0">Điện thoại</p>
                                                    </div>
                                                    <div class="col-sm-9">
                                                        <p class="text-muted mb-0">${USER.phoneNumber}</p>
                                                    </div>
                                                </c:if>
                                                <c:if var="test" test="${UPDATE_PROFILE_CHECKPOINT != null}">
                                                    <div class="input-group m-0">
                                                        <label class="input-group-text col-sm-3 fw-bold" for="phoneNumber">Điện thoại</label>
                                                        <input name="phoneNumber" type="tel" class="form-control col-sm-9" id="phoneNumber" value="${USER.phoneNumber}" required>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <c:if var="test" test="${UPDATE_PROFILE_CHECKPOINT == null}">
                                                    <div class="col-sm-3 fw-bold">
                                                        <p class="mb-0">Giới tính</p>
                                                    </div>
                                                    <div class="col-sm-9">
                                                        <p class="text-muted mb-0">${USER.gender ? "Nam" : "Nữ"}</p>
                                                    </div>
                                                </c:if>
                                                <c:if var="test" test="${UPDATE_PROFILE_CHECKPOINT != null}">
                                                    <div class="input-group m-0">
                                                        <label class="input-group-text col-3" for="typeName">Giới tính</label>
                                                        <select name="gender" id="typeName" class="form-select fw-bold col-8">
                                                            <option value="true" ${USER.gender == true ? 'selected' : ''}>Nam</option>
                                                            <option value="false" ${USER.gender == false ? 'selected' : ''}>Nữ</option>
                                                        </select>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <c:if var="test" test="${UPDATE_PROFILE_CHECKPOINT != null}">
                                                <br>
                                                <div class="row">
                                                    <div class="d-grid btn-group">
                                                        <a class="btn btn-danger col" href="${user_update}?cancel=true">Hủy bỏ</a>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if var="testPasswd" test="${UPDATE_PASSWORD_CHECKPOINT != null}">
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="container-sm">
                                                    <div class="alert alert-success" role="alert">
                                                        <h4 class="alert-heading">Thay đổi mật khẩu</h4>
                                                        <hr>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="input-group m-0">
                                                    <label class="input-group-text col-sm-3 fw-bold" for="fullname">Mật khẩu cũ</label>
                                                    <input name="oldPassword" type="password" class="form-control col-sm-9 rounded-end" id="fullname" required>
                                                    <button id="toggle-password" onclick="" type="button" class="btn btn-link" aria-label="Show password as plain text. Warning: this will display your password on the screen.">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </button>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="input-group m-0">
                                                    <label class="input-group-text col-sm-3 fw-bold" for="fullname">Mật khẩu mới</label>
                                                    <input name="newPassword" type="password" class="form-control col-sm-9 rounded-end" id="fullname" required>
                                                    <button id="toggle-password" type="button" class="btn btn-link" aria-label="Show password as plain text. Warning: this will display your password on the screen.">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </button>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="input-group m-0 ">
                                                    <label class="input-group-text col-sm-3 fw-bold" for="fullname">Nhập lại mật khẩu mới</label>
                                                    <input name="confirmNewPassword" type="password" class="form-control col-sm-9 rounded-end" id="fullname" spellcheck="false" autocorrect="off" autocapitalize="off" required>
                                                    <button id="toggle-password" type="button" class="btn btn-link" aria-label="Show password as plain text. Warning: this will display your password on the screen.">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </button>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="d-flex">
                                                    <button class="btn btn-success col-sm-9 me-1" type="submit" name="action" value="CONFIRM_UPDATE">Xác nhận</button>
                                                    <a class="btn btn-danger col-sm-3" href="${user_update}?cancel=true">Hủy bỏ</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </main>
        <footer class="text-center text-lg-start text-light bg-dark">
            <section class="">
                <div class="container text-center text-md-start mt-5">
                    <!-- Grid row -->
                    <div class="row mt-3 pt-3">
                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                            <!-- Content -->
                            <h6 class="text-uppercase fw-bold">Company name</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                Here you can use rows and columns to organize your footer
                                content
                            </p>
                        </div>
                        <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold">Products</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                <a href="#!" class="text-dark">MDBootstrap</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">MDWordPress</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">BrandFlow</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Bootstrap Angular</a>
                            </p>
                        </div>
                        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold">Useful links</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p>
                                <a href="#!" class="text-dark">Your Account</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Become an Affiliate</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Shipping Rates</a>
                            </p>
                            <p>
                                <a href="#!" class="text-dark">Help</a>
                            </p>
                        </div>
                        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                            <h6 class="text-uppercase fw-bold">Contact</h6>
                            <hr class="mb-4 mt-0 d-inline-block mx-auto myslash"/>
                            <p><i class="fas fa-home mr-3"></i> New York, NY 10012, US</p>
                            <p><i class="fas fa-envelope mr-3"></i> info@example.com</p>
                            <p><i class="fas fa-phone mr-3"></i></p>
                            <p><i class="fas fa-print mr-3"></i> + 01 234 567 89</p>
                        </div>
                    </div>
                </div>
            </section>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>