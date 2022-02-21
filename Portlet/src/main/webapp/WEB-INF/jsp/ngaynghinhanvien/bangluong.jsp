<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<portlet:renderURL var="addNew">
    <portlet:param name="action" value="addNew"/>
</portlet:renderURL>
<portlet:actionURL var="delete">
    <portlet:param name="action" value="delete"/>
</portlet:actionURL>
<portlet:actionURL var="addNewMore">
    <portlet:param name="action" value="addNewMore"/>
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value="" />
</portlet:renderURL>

<portlet:renderURL var="find">
    <portlet:param name="action" value=""/>
</portlet:renderURL>

<liferay-ui:success key="form-success"
                    message="Yêu cầu của bạn đã được thực hiện thành công !."/>
<liferay-ui:error key="form-error"
                  message="Yêu cầu của bạn thực hiện không thành công!"/>
<div class="admin-dashone-data-table-area">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline8-list shadow-reset">
                    <div class="sparkline8-hd">
                        <div class="main-sparkline8-hd text-center">
                            <h1 class="tieude">Bảng lương</h1>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <button class="btn btn-sm btn-danger login-submit-cs"
                                type="button"
                                onclick="location.href='${homeUrl}'">
                            <i class="fas fa-arrow-circle-left"></i>
                        </button>
                        <div class="datatable-dashv1-list custom-datatable-overright">
                            <table id="table" class="table-fit table table-striped table-borderd">
                                <thead>
                                <tr>
                                    <th class="align-middle text-center">STT</th>
                                    <th class="align-middle text-center">Họ Tên</th>
                                    <th class="align-middle text-center">Tháng</th>
                                    <th class="align-middle text-center">Ngày làm việc</th>
                                    <th class="align-middle text-center">Số ngày đã nghỉ</th>
                                    <th class="align-middle text-center">Tổng</th>
                                </tr>
                                </thead>
                                <tbody>
                                        <c:forEach var="item" items="${listView}" varStatus="num">
                                            <tr>
                                                <td class="align-middle text-center">${num.index +1}</td>
                                                <td class="align-middle text-center"
                                                    id="nhanVien.id">${item.nhanVien.tenNhanVien}</td>
                                                <td class="align-middle text-center" id="nam">1/2022</td>
                                                <td class="align-middle text-center" id="soNgayLamViec">21</td>
                                                <td class="align-middle text-center"
                                                    id="soNgayDaNghi">${item.soNgayDaNghi}</td>
                                                <td class="align-middle text-center"
                                                    id="soNgayNghiPhep"></td>
                                            </tr>
                                        </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var soNgayLamViec = $('#soNgayLamViec'),
        soNgayDaNghi = $('#soNgayDaNghi'),
        tongNgayLam = $('#tongTienDaDuyet')
    hoanCongTacPhi.text(function () {
        var sum = 0
        sum = Number(soTienDaUng.text().replace(/[^0-9.-]+/g, "")) - Number(tongTienDaDuyet.text().replace(/[^0-9.-]+/g, ""));
        var formatter = new Intl.NumberFormat('en-US', {
            style: 'decimal',
            maximumFractionDigits: 2,
        });
        return formatter.format(sum);
    })
</script>