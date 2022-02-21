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
<portlet:renderURL var="bangLuong">
    <portlet:param name="action" value="bangLuong"/>
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
                            <h1 class="tieude">Bảng chấm công</h1>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <button class="btn btn-sm btn-danger login-submit-cs"
                                type="button"
                                title="Quay Lại"
                                onclick="location.href='${homeUrl}'">
                            <i class="fas fa-arrow-circle-left"></i>
                        </button>
                        <button class="btn btn-sm btn-info login-submit-cs"
                                type="button"
                                title="Bảng lương"
                                onclick="location.href='${bangLuong}'">
                            <i class="fas fa-money-check-alt"></i>
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
                                    <th class="align-middle text-center" id="calculate" onclick="calculate()">Tổng</th>

                                </tr>
                                </thead>
                                <tbody>
                                        <c:forEach var="item" items="${listView}" varStatus="num">
                                            <tr>
                                                <td class="align-middle text-center">${num.index +1}</td>
                                                <td class="align-middle text-center"
                                                    id="nhanVien.id">${item.nhanVien.tenNhanVien}</td>
                                                <td class="align-middle text-center"  id="nam">1/2022</td>
                                                <td class="sum sum1 align-middle text-center" name="soNgayLamViec" id="soNgayLamViec">21.0</td>
                                                <td class="sum sum2 align-middle text-center" name="soNgayDaNghi" onclick="calc()"
                                                    id="soNgayDaNghi">${item.soNgayDaNghi}</td>
                                                <td class="subtract align-middle text-center" name="tong"
                                                    id="tong"></td>
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
    function calculate() {
        var sum;
        var difference;
        $("tbody tr").each(function() {
            sum = 0;
            difference = 0;
            $(this).find(".sum").each(function() {
                sum += parseFloat($(this).html());
            });
            difference = parseFloat($(this).find(".sum1").html()) - parseFloat($(this).find(".sum2").html());
            $(this).find(".subtract").html(difference);
        })
    }
</script>