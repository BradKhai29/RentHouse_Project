<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Rent House Search Page</title>
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
                    </div>
                    <div class="col-4 text-center">
                        <a class="blog-header-logo text-dark text-uppercase" href="${root}">
                            Renty
                        </a>
                    </div>
                    <div class="col-4 d-flex justify-content-end align-items-center">
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
                                    <li><a class="dropdown-item" href="${user}?action=update">Cài đặt tài khoản</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <c:if test="${USER.userRole}">
                                        <li><a class="dropdown-item" href="${rent_house}">Đăng bài</a></li>
                                        <li><hr class="dropdown-divider"></li>
                                    </c:if>
                                    <li><a class="dropdown-item" href="${user}?action=logout">Đăng xuất</a></li>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                </div>
            </section>
        </header>
        <main class="container">
            <section class="p-4 p-md-5 mb-4 text-white rounded bg-dark" id="jumbotron">
                <div class="col-md-6 px-0">
                    <h1 class="display-4">Danh sách nhà trọ yêu thích</h1>
                </div>
            </section>
            <section class="album py-5 mt-0 bg-light">
                <div class="container">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
                        <c:forEach var="rentHouseEntry" items="${rentHouseMap}">
                            <c:set var="rentHouse" value="${rentHouseEntry.value}"></c:set>
                            <c:if test="${USER.favorRentHouseMap.containsKey(rentHouse.houseID)}">
                                <div class="col" id="rentHouse${rentHouse.houseID}">
                                    <form class="card shadow-sm" action="${rent_house_detail}" method="post">
                                        <input type="text" name="houseID" value="${rentHouse.houseID}" hidden>
                                        <div class="position-relative">
                                            <button type="submit" class="card-img-top border-0 p-0 m-0">
                                                <img width="100%" height="320" class="card-img-top" src="${root}/${rentHouse.imgURL}" alt="">
                                            </button>
                                        </div>
                                        <ul class="card-body m-0 py-0">
                                            <div class="d-flex justify-content-between align-items-center pt-2">
                                                <div class="fw-bold">
                                                    <span class="fw-bold text-primary">itemNo = ${rentHouse.itemNo} Nhà trọ ${rentHouse.houseID}</span>
                                                </div>
                                            </div>
                                            <p class="mb-1">${rentHouse.houseName}</p>
                                            <p class="mb-1">
                                                <c:forEach var="i" begin="1" end="3">
                                                    <i class="fa-solid fa-star text-warning"></i>
                                                </c:forEach>
                                                <c:forEach var="i" begin="1" end="2">
                                                    <i class="fa-solid fa-star text-muted"></i>
                                                </c:forEach>
                                            </p>
                                        </ul>
                                        <div class="card-body row mt-0 pt-2">
                                            <div class="col">
                                                <span>
                                                    <i class="fa-solid fa-dollar-sign"></i>
                                                    Giá: ${rentHouse.price}
                                                </span>
                                            </div>
                                            <div class="col">
                                                <span>
                                                    <i class="fa-solid fa-layer-group"></i>
                                                    ${rentHouse.area} m<sup>2</sup>
                                                </span>
                                            </div>
                                        </div>
                                        <hr class="dropdown-divider mb-0">
                                        <div class="card-footer">
                                            <div class="d-flex justify-content-between align-items-center pt-1">
                                                <div class="card-title">
                                                    ${rentHouse.streetNumber} ${rentHouse.streetName}, ${rentHouse.districtName}
                                                </div>
                                                <section class="card-title">
                                                    <c:if test="${USER.userID == rentHouse.providerID}">
                                                        <div class="btn-group dropup">
                                                            <input type="number" name="houseID" value="${rentHouse.houseID}" hidden>
                                                            <i class="fa-solid fa-sliders text-warning" data-bs-toggle="dropdown" aria-expanded="false"></i>
                                                            <ul class="dropdown-menu">
                                                                <li><button class="dropdown-item" formaction="${rent_house}" type="submit" name="action" value="get_update">Chỉnh sửa thông tin</button></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><button class="dropdown-item" formaction="${rent_house}" type="submit" name="action" value="delete">Xóa bài đăng</button></li>
                                                            </ul>
                                                        </div>

                                                    </c:if>
                                                </section>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </section>
        </main>
        <footer class="container-fluid">
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <script>
            function changeMode(element) {
                var classMode1 = "px-3 py-2 btn mybtn";
                var classMode2 = "px-3 py-2 btn mybtn-outline";

                var currentClassMode = element.className;
                if(currentClassMode == classMode1) currentClassMode = classMode2;
                else currentClassMode = classMode1;

                element.className = currentClassMode;
            }
        </script>
    </body>
</html>