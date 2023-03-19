<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Detail rent house page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=devce-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
        <link rel="stylesheet" href="${root}/assets/blog.css">
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
                            <a class="btn btn-outline-danger mybtn-outline" aria-current="page" href="${user}">Đăng nhập</a>
                            <a class="btn mybtn ms-3" href="${user}?action=register">Đăng ký</a>
                        </c:if>
                        <c:if test="${USER != null}">
                            <div class="dropdown">
                                <button class="btn mybtn dropdown-toggle ms-3" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa-solid fa-circle-user icon"></i> ${USER.username}
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><a class="dropdown-item" href="#">Cài đặt tài khoản</a></li>
                                    <li><hr class="dropdown-divider"></li>
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
        <main class="container mt-4">
            <section class="row mb-4 rounded-3">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0 fw-bold p-3 bg-light">
                        <li class="breadcrumb-item">
                            <a class="text-decoration-none text-muted" href="${root}">Nhà trọ</a>
                        </li>
                        <li class="breadcrumb-item" aria-current="page">
                            <a class="text-decoration-none text-muted" href="${rent_house_search}?districtID=${rentHouse.districtID}">Quận ${rentHouse.districtName}</a>
                        </li>
                        <li class="breadcrumb-item" aria-current="page">
                            <a class="text-decoration-none text-muted" href="${rent_house_search}?streetID=${rentHouse.streetID}">Đường ${rentHouse.streetName}</a>
                        </li>
                        <li class="breadcrumb-item" aria-current="page">
                            Nhà trọ ${rentHouse.houseName}
                        </li>
                    </ol>
                </nav>
            </section>
            <div class="row">
                <div class="col-8">
                    <div class="white-box rounded-3">
                        <img id="output" class="card-img-top img-fluid rounded-3"
                             width="100%"
                             src="${root}/${rentHouse.imgURL}"
                             alt="product image">
                    </div>
                </div>
                <div class="col-4">
                    <div class="card mb-4">
                        <div class="card-body text-center">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                                 class="rounded-circle img-fluid" style="width: 150px;">
                            <p class="text-muted fs-5 fw-light">Được đăng bởi</p>
                            <div class="my-0 py-0 fs-4">${rentHouse.provider.fullname}</div>
                            <div class="d-flex justify-content-center mt-2">
                                <button class="btn btn-outline-danger fw-bold">
                                    <i class="fa-solid fa-square-phone"></i> Liên hệ: ${rentHouse.provider.phoneNumber}
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body bg-light py-2">
                            <p class="card-title fw-bold mb-0 text-muted fs-5 d-flex align-items-center justify-content-between">
                                <span>Thông tin mô tả</span>
                                <button class="btn btn-outline-danger">
                                    Yêu thích <i class="fa-regular fa-heart"></i>
                                </button>
                            </p>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item d-flex justify-content-between">
                                <span class="text-muted"><i class="fa-solid fa-dollar-sign pe-2"></i>Giá</span>
                                <span>${rentHouse.price}</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between">
                                <span class="text-muted"><i class="fa-solid fa-layer-group pe-1"></i>Diện tích</span>
                                <span>${rentHouse.area} m<sup>2</sup></span>
                            </li>
                            <li class="list-group-item">
                                <span class="text-muted"><i class="fa-solid fa-circle-info pe-1"></i>
                                    Mô tả:
                                </span>
                                <span>${rentHouse.details}</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </main>
        <footer class="container mt-4">
            <c:if test="${empty USER}">
                <section class="col-8">
                    <div class="d-flex justify-content-end px-2">
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
                </section>
            </c:if>
            <div class="col-8 mt-2 bg-dark bg-opacity-25 rounded-3">
                <c:set var="haveComment" value="${false}" scope="page"></c:set>
                    <div class="my-5 py-5 text-dark container-fluid">
                        <div class="row d-flex justify-content-center">
                            <div class="card col-10">
                            <c:forEach var="commentEntry" items="${rentHouse.commentMap}">
                                <div class="card-body p-4">
                                    <c:set var="comment" value="${commentEntry.value}"></c:set>
                                    <c:if var="test" test="${comment.userID == USER.userID}">
                                        <c:set var="haveComment" value="${haveComment || true}" scope="page"></c:set>
                                    </c:if>
                                    <section class="row">
                                        <div class="col-1 d-flex align-items-center px-0">
                                            <img class="rounded-circle img-fluid"
                                                 src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(21).webp" alt="avatar"/>
                                        </div>
                                        <c:if test="${comment.userID != USER.userID}">
                                            <div class="col-11">
                                                <div class="card">
                                                    <div class="card-header text-black-50 fs-5 d-flex justify-content-between">
                                                        <span>user 1</span>
                                                        <span>
                                                            <c:forEach var="index" begin="1" end="5">
                                                                <i class="fa-solid fa-star text-warning"></i>
                                                            </c:forEach>
                                                        </span>
                                                    </div>
                                                    <div class="card-body">
                                                        <p class="card-text">
                                                            ${comment.details}
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${comment.userID == USER.userID}">
                                            <form class="col-11" action="${user_comment}" method="post">
                                                <input type="number" name="commentID" value="${comment.commentID}" hidden>
                                                <div class="card">
                                                    <div class="card-header text-black-50 fs-5 d-flex justify-content-between">
                                                        <span>user 1</span>
                                                        <span>
                                                            <c:forEach var="index" begin="1" end="5">
                                                                <i class="fa-solid fa-star text-warning"></i>
                                                            </c:forEach>
                                                        </span>
                                                    </div>
                                                    <div class="card-body">
                                                        <c:if test="${sessionScope.EDIT == null}">
                                                            <p class="card-text">
                                                                ${comment.details}
                                                            </p>
                                                        </c:if>
                                                        <c:if test="${sessionScope.EDIT != null}">
                                                            <div class="card-text">
                                                                <textarea class="form-control" id="text" rows="4" name="details" required>${comment.details}</textarea>
                                                            </div>
                                                        </c:if>
                                                        <button class="mt-2 btn btn-outline-danger" type="submit" name="action" value="delete" class="btn btn-danger">
                                                            Xóa Comment <i class="fa-solid fa-trash"></i>
                                                        </button>
                                                        <button class="mt-2 btn btn-warning" type="submit" name="action" value="${EDIT == null ? 'edit' : 'confirm_update'}" class="btn btn-danger">
                                                            Chỉnh sửa <i class="fa-solid fa-pen-to-square"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </c:if>
                                    </section>
                                </div>
                            </c:forEach>
                            <c:if test="${USER != null && haveComment == false}">
                                <form class="col-11" action="${user_comment}" method="post">
                                    <input name="action" value="comment" hidden>
                                    <div class="card">
                                        <div class="card-header text-black-50 fs-5 d-flex justify-content-between">
                                            <span>Comment về nhà trọ</span>
                                            <span>
                                                <c:forEach var="index" begin="1" end="5">
                                                    <i class="fa-solid fa-star text-warning"></i>
                                                </c:forEach>
                                            </span>
                                        </div>
                                        <div class="card-body">
                                            <div class="card-text">
                                                <textarea class="form-control" id="text" rows="4" name="details" required>${comment.details}</textarea>
                                            </div>
                                            <button class="mt-2 btn btn-warning" type="submit" class="btn btn-danger">
                                                Gửi comment <i class="fa-solid fa-pen-to-square"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>