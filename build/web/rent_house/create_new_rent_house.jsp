<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if var="test" test="${sessionScope.USER == null}">
    <jsp:forward page="${root}"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>Create new Rent House Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
    </head>
    <body>
        <header class="navbar navbar-expand navbar-dark sticky-top bg-dark flex-md-nowrap p-0 mb-5 shadow">
            <a href="${root}" class="navbar-brand col-md-3 col-lg-2 me-0 px-3">
                <i class='fa-solid fa-house'></i>Trang chủ
            </a>
            <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="bg-dark w-100 text-center text-light fw-bold">ĐĂNG BÀI PHÒNG TRỌ</div>
            <ul class="navbar-nav ms-auto ms-md-0 bg-danger align-items-center justify-content-center">
                <form class="nav-item dropdown px-2" action="admin" method="post">
                    <a class="nav-link dropdown-toggle text-dark" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user fa-fw"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${user}?action=update">Cài đặt tài khoản</a></li>
                        <li><hr class="dropdown-divider"/></li>
                        <li><a class="dropdown-item" href="${user}?action=logout">Đăng xuất</a></li>
                    </ul>
                </form>
            </ul>
        </header>
        <main class="container">
            <div class="row">
                <nav aria-label="breadcrumb" class="bg-dark text-light rounded-3 p-3 px-5 mb-4">
                    <ol class="breadcrumb mb-0 fw-bold text-light">
                        <li class="breadcrumb-item">
                            <a href="${root}" class="text-light">Trang chủ</a>
                        </li>
                        <li class="breadcrumb-item active text-light" aria-current="page">
                            <a href="${root}/user" class="text-light">Quản lý bài đăng</a>
                        </li>
                        <li class="breadcrumb-item active text-light" aria-current="page">Tạo bài đăng</li>
                    </ol>
                </nav>
            </div>
            <div class="row">
                <div class="card">
                    <div class="card-body">
                        <form class="row" action="${root}/user/rent_house" method="post">
                            <div class="col-lg-5 col-md-5 col-sm-6 rounded-3">
                                <div class="white-box text-center rounded-3">
                                    <label for="productImg" class="form-label">
                                        <picture class="card-img-top rounded-3" width="100%" height="340px">
                                            <img id="output" class="card-img-top rounded-3"
                                            width="100%" height="340px"
                                            src="${root}/rent_house/placeholder.png"
                                            alt="product image">
                                        </picture>
                                    </label>
                                    <input name="productImg" class="form-control" type="file"
                                    accept=".png, .jpg, .jpeg, .svg, .webp"
                                    id="productImg" onchange="loadFile(event)" formenctype="multipart/form-data">
                                </div>
                            </div>
                            <section class="col-lg-7 col-md-7 col-sm-6 mt-0">
                                <div class="d-flex justify-content-start mb-2">
                                    <!-- Button section -->
                                    <a href="${root}/" class="rounded-3 btn btn-primary border-2 fw-bold">
                                        <i class="fa-solid fa-backward"></i> Về lại trang chủ
                                    </a>
                                </div>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Tên nhà trọ</span>
                                    <input type="text" name="houseName" class="form-control fw-bold col-8" value="${createRentHouse.houseName}" ${STEP2 == null ? 'required' : 'disabled'}>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Mô tả nhà trọ</span>
                                    <textarea name="details" class="form-control fw-bold col-8" ${STEP2 == null ? 'required' : 'disabled'}>${createRentHouse.details}</textarea>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Diện tích</span>
                                    <input type="number" name="area" class="form-control fw-bold col-8" value="${createRentHouse.area}" ${STEP2 == null ? 'required' : 'disabled'}>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Giá cho thuê</span>
                                    <input type="number" name="price" class="form-control fw-bold col-8" value="${createRentHouse.price}" ${STEP2 == null ? 'required' : 'disabled'}>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <label class="input-group-text col-3" for="typeName">Tên Quận</label>
                                    <select name="districtID" id="typeName" class="form-select fw-bold col-8" ${STEP2 == null ? 'required' : 'disabled'}>
                                        <c:forEach var="districtEntry" items="${applicationScope.districtMap}">
                                            <c:set var="district" value="${districtEntry.value}"></c:set>
                                            <option value="${districtEntry.key}" ${createRentHouse.districtID == districtEntry.key ? 'selected' : ''}>${district.districtName}</option>
                                        </c:forEach>
                                    </select>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <label class="input-group-text col-3" for="typeName">Tên Đường</label>
                                    <select name="streetID" id="typeName" class="form-select fw-bold col-8" ${STEP2 == null ? 'disabled' : 'required'}>
                                        <c:forEach var="streetEntry" items="${applicationScope.streetMap}">
                                            <c:set var="street" value="${streetEntry.value}"></c:set>
                                            <c:if test="${street.districtID == createRentHouse.districtID}">
                                                <option value="${streetEntry.key}" ${createRentHouse.streetID == streetEntry.key ? 'selected' : ''}>${street.streetName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </h5>
                                <div class="btn-group d-flex gap-2">
                                    <!-- Create button section -->
                                    <button type="submit" name="action" value="${STEP2 == null ? 'create' : 'confirm_create'}"
                                    class="col-8 rounded-3 btn btn-warning border-2 fw-bold">
                                        ${STEP2 == null ? 'Tiếp tục' : 'Xác nhận đăng bài'}
                                    </button>
                                    <!-- Cancel Button section -->
                                    <button class="col-3 rounded-3 btn btn-danger border-2 fw-bold" type="button" data-bs-toggle="modal" data-bs-target="#cancelCreate">
                                        Hủy đăng bài
                                    </button>
                                    <!-- Cancel Modal -->
                                    <div class="modal fade" id="cancelCreate" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="cancelCreateLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="staticBackdropLabel">Bạn định hủy đăng bài</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    Mọi thông tin của bài đăng sẽ không được lưu lại, bạn chắc chứ?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary fw-bold" data-bs-dismiss="modal">Không hủy</button>
                                                    <a href="${root}/user/rent_house" class="btn btn-primary fw-bold">Xác nhận hủy tạo mới</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </form>
                    </div>
                </div>
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
        <script>
            var loadFile = function (event) {
                var output = document.getElementById('output');
                output.src = URL.createObjectURL(event.target.files[0]);
                output.onload = function () {
                    URL.revokeObjectURL(output.src);
                }
            };
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>