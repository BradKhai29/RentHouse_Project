<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Default JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=devce-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
    </head>
    <body>
        <header class="container-fluid">
        </header>
        <main class="container-fluid">
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
                        <section class="row">
                            <div class="col-lg-5 col-md-5 col-sm-6 rounded-3">
                                <div class="white-box text-center rounded-3">
                                    <label for="productImg" class="form-label">
                                        <picture class="card-img-top rounded-3" width="100%" height="340px">
                                            <img id="output" class="card-img-top rounded-3"
                                                 width="100%" height="340px"
                                                 src="${root}/${rentHouse.imgURL}"
                                                 alt="product image">
                                        </picture>
                                    </label>
                                </div>
                            </div>
                            <section class="col-lg-7 col-md-7 col-sm-6 mt-0">
                                <div class="d-flex justify-content-start mb-2">
                                    <!-- Button section -->
                                    <a href="${root}" class="rounded-3 btn btn-primary border-2 fw-bold">
                                        <i class="fa-solid fa-backward"></i> Về lại trang chủ
                                    </a>
                                </div>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Tên nhà trọ</span>
                                    <input type="text" name="houseName" class="form-control fw-bold col-8" value="${rentHouse.houseName}" disabled>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Mô tả nhà trọ</span>
                                    <textarea name="details" class="form-control fw-bold col-8" disabled>${rentHouse.details}</textarea>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Diện tích</span>
                                    <input type="number" name="area" class="form-control fw-bold col-8" value="${rentHouse.area}" disabled>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <span class="input-group-text col-3" id="inputGroup-sizing-default">Giá cho thuê</span>
                                    <input type="number" name="price" class="form-control fw-bold col-8" value="${rentHouse.price}" disabled>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <label class="input-group-text col-3" for="typeName">Tên Quận</label>
                                    <select class="form-select fw-bold col-8" disabled>
                                        <option>${rentHouse.districtName}</option>
                                    </select>
                                </h5>
                                <h5 class="input-group mb-3">
                                    <label class="input-group-text col-3" for="typeName">Tên Đường</label>
                                    <select class="form-select fw-bold col-8" disabled>
                                        <option>${rentHouse.streetName}</option>
                                    </select>
                                </h5>
                            </section>
                        </section>
                    </div>
                </div>
            </div>
        </main>
        <footer class="container-fluid">
            <c:if test="${empty USER}">
                <div class="row">
                    <button class="btn btn-warning"
                    data-bs-toggle="modal"
                    data-bs-target="#requireLogin">Comment</button>
                    <div class="modal fade" id="requireLogin" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <h5 class="modal-title" id="staticBackdropLabel">Vui lòng đăng nhập hoặc đăng ký để Comment</h5>
                                </div>
                                <div class="modal-footer">
                                    <a href="${user}" class="btn btn-danger fw-bold">Đăng nhập</a>
                                    <a href="${user}?action=register" class="btn btn-success fw-bold">Đăng ký</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <c:set var="haveComment" value="${false}" scope="page"></c:set>
                <c:forEach var="commentEntry" items="${rentHouse.commentMap}">
                    <c:set var="comment" value="${commentEntry.value}"></c:set>
                    <c:if var="test" test="${comment.userID == USER.userID}">
                        <c:set var="haveComment" value="${haveComment || true}" scope="page"></c:set>
                    </c:if>
                    <div style="background-color: #d94125;">
                        <div class="container my-5 py-5 text-dark">
                            <div class="row d-flex justify-content-center">
                                <div class="col-md-10 col-lg-8 col-xl-6">
                                    <div class="card">
                                        <div class="card-body p-4">
                                            <div class="d-flex flex-start w-100">
                                                <img class="rounded-circle shadow-1-strong me-3"
                                                    src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(21).webp" alt="avatar" width="65"
                                                    height="65"/>
                                                <div class="w-100">
                                                    <h5>Comment về nhà trọ</h5>
                                                    <ul class="rating mb-3" data-mdb-toggle="rating">
                                                        <i class="far fa-star fa-sm text-danger" title="Tệ"></i>
                                                        <i class="far fa-star fa-sm text-danger" title="Vừa"></i>
                                                        <i class="far fa-star fa-sm text-danger" title="Ổn"></i>
                                                        <i class="far fa-star fa-sm text-danger" title="Tốt"></i>
                                                        <i class="far fa-star fa-sm text-danger" title="Rất tốt"></i>
                                                    </ul>
                                                    <c:if test="${comment.userID != USER.userID}">
                                                        <div class="form-outline">
                                                            ${comment.details}
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${comment.userID == USER.userID}">
                                                        <form action="${user_comment}" method="post">
                                                            <input type="number" name="commentID" value="${comment.commentID}" hidden>
                                                            <c:if test="${sessionScope.EDIT == null}">
                                                                <div class="form-outline">${comment.details}</div>
                                                                <button type="submit" name="action" value="edit" class="btn btn-danger">
                                                                    Chỉnh sửa<i class="fas fa-long-arrow-alt-right ms-1"></i>
                                                                </button>
                                                            </c:if>
                                                            <c:if test="${sessionScope.EDIT != null}">
                                                                <div class="form-outline">
                                                                    <textarea class="form-control" id="text" rows="4" name="details" required>${comment.details}</textarea>
                                                                </div>
                                                                <button type="submit" name="action" value="confirm_update" class="btn btn-danger">
                                                                    Chỉnh sửa<i class="fas fa-long-arrow-alt-right ms-1"></i>
                                                                </button>
                                                            </c:if>
                                                        </form>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${not empty USER && haveComment == false}">
                <form style="background-color: #d94125;" action="${user_comment}" method="post">
                        <input name="action" value="comment" hidden>
                        <div class="container my-5 py-5 text-dark">
                            <div class="row d-flex justify-content-center">
                                <div class="col-md-10 col-lg-8 col-xl-6">
                                    <div class="card">
                                        <div class="card-body p-4">
                                            <div class="d-flex flex-start w-100">
                                                <img class="rounded-circle shadow-1-strong me-3"
                                                    src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(21).webp" alt="avatar" width="65"
                                                    height="65"/>
                                                <div class="w-100">
                                                    <h5>Comment về nhà trọ</h5>
                                                    <ul class="rating mb-3" data-mdb-toggle="rating">
                                                        <i class="far fa-star fa-sm text-danger" title="Tệ"></i>
                                                        <i class="far fa-star fa-sm text-danger" title="Vừa"></i>
                                                        <i class="far fa-star fa-sm text-danger" title="Ổn"></i>
                                                        <i class="far fa-star fa-sm text-danger" title="Tốt"></i>
                                                        <i class="far fa-star fa-sm text-danger" title="Rất tốt"></i>
                                                    </ul>
                                                    <div class="form-outline">
                                                        <textarea class="form-control" id="text" rows="4" placeholder="Cảm nhận của bạn" name="details" required></textarea>
                                                    </div>
                                                    <div class="d-flex justify-content-between mt-3">
                                                        <button type="submit" class="btn btn-danger">
                                                            Gửi<i class="fas fa-long-arrow-alt-right ms-1"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </form>
            </c:if>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>