<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
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
    <a href="${user}?action=register">Get to register page</a>
    ${USER == null ? '' : USER.username}
    <c:if test="${USER != null}">
        <div class="text-danger">
            <br><a href="${rent_house}">Tạo bài đăng</a>
        </div>
     	<br><a href="${user}?action=logout">Đăng xuất</a>
    </c:if>
    <br>
    <a href="${user}">Đăng nhập</a>
        <header class="container-fluid">
        </header>
        <main class="container-fluid">
            <div class="row">
                <div class="container">
                    <div class="row">Tìm kiếm theo quận</div>
                    <form class="row d-flex" action="${rent_house_search}" method="post">
                        <c:forEach var="districtEntry" items="${selectedDistrictMap}">
                            <c:set var="district" value="${districtEntry.value}"></c:set>
                            <div class="col-1 d-inline">
                                <label class="btn btn-success" for="district${district.districtID}">${district.districtName}</label>
                                <input type="checkbox" name="districtID" value="${district.districtID}" id="district${district.districtID}" ${district.selected ? 'checked' : ''}>
                            </div>
                        </c:forEach>
                        <button class="btn btn-success" type="submit">
                            Search
                        </button>
                    </form>
                </div>
            </div>
            <section class="album py-5 mt-0 bg-light">
                <div class="container">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        <c:forEach var="rentHouse" items="${selectedRentHouseList}">
                            <div class="col" id="rentHouse${rentHouse.houseID}">
                                <form class="card shadow-sm" action="${rent_house_detail}" method="post">
                                    <input type="text" name="houseID" value="${rentHouse.houseID}" hidden>
                                    <div>
                                        <button type="submit" class="card-img-top border-0 p-0 m-0">
                                            <img width="100%" height="320" class="card-img-top" src="${root}/${rentHouse.imgURL}" alt="">
                                        </button>
                                    </div>
                                    <ul class="card-body m-0 py-0">
                                        <div class="d-flex justify-content-between align-items-center pt-2">
                                            <div class="fw-bold">
                                                <span class="fw-bold text-primary">Tên nhà trọ</span> : ${rentHouse.houseName}
                                            </div>
                                        </div>
                                    </ul>
                                    <hr class="dropdown-divider mb-0">
                                    <div class="card-body mt-0">
                                        <div class="d-flex justify-content-between align-items-center pt-2">
                                            <h5 class="card-title">Giá cho thuê</h5>
                                            <span class="badge fs-5 bg-warning text-muted">${rentHouse.price} VND</span>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center pt-2">
                                            <h5 class="card-title">Địa chỉ</h5>
                                            <span class="badge fs-5 bg-warning text-muted">${rentHouse.streetNumber} ${rentHouse.streetName}, ${rentHouse.districtName}</span>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center pt-1">
                                            <div class="btn-group">
                                                <input type="text" name="productID" value="${product.productID}" hidden>
                                                <button type="submit" class="rounded-start btn btn-outline-secondary">Xem chi tiết</button>
                                                <button type="submit" class="btn btn-outline-secondary">
                                                    Yêu thích
                                                </button>
                                            </div>
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
    </body>
</html>