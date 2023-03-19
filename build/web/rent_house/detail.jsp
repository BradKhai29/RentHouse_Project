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
                        <a class="blog-header-logo text-dark" href="#">
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
        <main class="container">
            <div class="row">
                <div class="col-8">
                    <div class="white-box rounded-3">
                        <img id="output" class="card-img-top img-fluid rounded-3"
                                 width="100%" height="300px"
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
                            <h4 class="my-0 py-0">${rentHouse.provider.fullname}</h4>
                            <div class="d-flex justify-content-center mb-2">
                                <button class="btn btn-success fw-bold">
                                    Liên hệ: ${rentHouse.provider.phoneNumber}
                                </button>
                            </div>
                        </div>
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