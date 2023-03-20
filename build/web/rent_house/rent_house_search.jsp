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
                        <a class="blog-header-logo text-dark text-uppercase" href="#">
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
                                    <li><a class="dropdown-item" href="${rent_house}">Đăng bài</a></li>
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
            <section class="p-4 p-md-5 mb-4 text-white rounded bg-dark" id="jumbotron">
                <div class="col-md-6 px-0">
                    <h1 class="display-4">Tìm nhà trọ</h1>
                </div>
            </section>
            <div class="row">
                <p class="fs-6 text-uppercase">Tìm kiếm theo quận</p>
            </div>
            <form class="row my-2" action="${rent_house_search}" method="post">
                <div class="col d-flex justify-content-start pe-0">
                    <section>
                        <div class="nav d-flex justify-content-start">
                            <c:forEach var="districtEntry" items="${districtMap}">
                                <c:set var="district" value="${districtEntry.value}"></c:set>
                                <div class="btn border-0 me-2 d-inline p-0">
                                    <input hidden type="checkbox" name="districtID" value="${district.districtID}"
                                    id="district${district.districtID}"
                                    ${district.selected ? 'checked' : ''}>
                                    <label class="px-3 py-2 btn ${district.selected ? 'mybtn' : 'mybtn-outline'}"
                                    for="district${district.districtID}"
                                    onclick="changeMode(this)">${district.districtName}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </section>
                </div>
                <div class="col-3 ps-0 d-flex justify-content-end">
                    <button class="btn mybtn" type="submit">Tìm kiếm</button>
                </div>
            </form>
            <section class="album py-5 mt-0 bg-light">
                <div class="container">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
                        <c:forEach var="rentHouse" items="${selectedRentHouseList}">
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
                                                <span class="fw-bold text-primary">Nhà trọ</span>
                                            </div>
                                        </div>
                                        <p class="mb-1">${rentHouse.houseName}</p>
                                        <p class="mb-1">
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
                                            <i class="fa-solid fa-star"></i>
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
                                                Diện tích: ${rentHouse.area} m<sup>2</sup>
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