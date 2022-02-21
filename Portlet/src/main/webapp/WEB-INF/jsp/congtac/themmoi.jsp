<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp"%>
<portlet:actionURL var="add">
    <portlet:param name="action" value="add" />
</portlet:actionURL>

<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value="" />
    <portlet:param name="id" value="${duAnId}" />
</portlet:renderURL>

<c:if test="${empty item.id or item.id == 0}">
    <c:set var="tieuDe">
        Thêm mới
    </c:set>
</c:if>
<c:if test="${item.id > 0}">
    <c:set var="tieuDe">
        Chỉnh sửa
    </c:set>

</c:if>

<liferay-ui:error key="alert-error"
                  message="Yêu cầu của bạn thực hiện không thành công!." />

<div class="basic-form-area mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline12-list shadow-reset">
                    <div class="sparkline12-hd">
                        <div class="main-sparkline12-hd text-center">
                            <h4 class="tieude">Dự án:&nbsp${tenDuAn}</h4>
                            <h4 class="tieude">${tieuDe}</h4>
                        </div>
                    </div>

                    <div class="sparkline12-graph">
                        <div class="basic-login-form-ad">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="all-form-element-inner">
                                        <form:form action="${add}" method="post" name="submitForm"
                                                   modelAttribute="item" id="save">
                                            <form:hidden path="id"/>
                                            <form:hidden path="duAn.id" value="${duAnId}"/>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <label class="col-form-label">Người phụ trách:&nbsp;</label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <form:select path="nhanVien.id" class="form-select" id="nhanVien" name="nhanVien">
                                                            <form:options items="${listNhanVien}" itemLabel="tenNhanVien" itemValue="id"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <form:label path="ngayBatDau" class="col-form-label">Ngày bắt đầu:&nbsp;</form:label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <form:input path="ngayBatDau" id="datepicker" class="form-control" placeholder="dd/mm/yyyy"/>
                                                        <form:errors path="ngayBatDau" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <form:label path="ngayKetThuc" class="col-form-label">Ngày kết thúc:&nbsp;</form:label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <form:input path="ngayKetThuc" id="datepicker2" class="form-control" placeholder="dd/mm/yyyy"/>
                                                        <form:errors path="ngayKetThuc" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <form:label path="nhiemVu" class="col-form-label">Nhiệm vụ:&nbsp;</form:label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <form:input path="nhiemVu" id="nhiemVu" class="form-control"/>
                                                        <form:errors path="nhiemVu" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <form:label path="diaDiem" class="col-form-label">Địa điểm:&nbsp;</form:label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <form:input path="diaDiem" id="diaDiem" class="form-control"/>
                                                        <form:errors path="diaDiem" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <form:label path="ghiChu" class="col-form-label">Ghi chú:&nbsp;</form:label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <form:textarea path="ghiChu" id="ghiChu" class="form-control"/>
                                                        <form:errors path="ghiChu" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group-inner m-3 d-none">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <form:label path="trangThai" class="col-form-label">Trạng thái:&nbsp;</form:label><label class="text-danger">&#10031;</label>
                                                    </div>
                                                    <div class="col-lg-10 mt-2">
                                                        <form:radiobutton path="trangThai" id="trangThai" checked="checked" value="MOICAPNHAT"/><label>&ensp;Mới cập nhật&ensp;</label>
                                                        <c:forEach var="role" items="${userRoles}">
                                                        <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                                                        <form:radiobutton path="trangThai" id="trangThai" checked="checked" value="DAUNG"/><label>&ensp;Đã ứng&ensp;</label>
                                                        </c:if>
                                                        </c:forEach>

                                                    </div>
                                                </div>
                                            </div>

                                            <c:forEach var="role" items="${userRoles}">
                                                <c:if test="${(role.getName() == 'Administrator') || (role.getName() == 'Kế toán')}">
                                                    <div class="form-group-inner m-3">
                                                        <div class="row">
                                                            <div class="col-lg-2">
                                                                <form:label path="tienUng" class="col-form-label">Tiền ứng:&nbsp;</form:label>
                                                            </div>
                                                            <div class="col-lg-10">
                                                                <form:input path="tienUng" id="tienUng" class="form-control CurrencyInput" />
                                                                <form:errors path="tienUng" cssClass="error text-danger" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                            <div class="form-group-inner m-3">
                                                <div class="login-btn-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3"></div>
                                                        <div class="col-lg-9">
                                                            <div class="login-horizental cancel-wp pull-center">
                                                                <button class="btn btn-sm btn-primary login-submit-cs" type="submit" onclick="submitTienUng()">Lưu</button>
                                                                <button class="btn btn-sm btn-danger login-submit-cs" type="button" onclick="location.href='${homeUrl}'">Quay lại</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </form:form>
                                        <script>
                                            $( function() {
                                                $.datepicker.setDefaults({
                                                    dateFormat:"dd/mm/yy",
                                                    changeMonth: true,
                                                    changeYear: true,
                                                    minDate: 0,
                                                    onClose:function(date, inst){
                                                        $("#selectedDtaeVal").html(date);
                                                    }
                                                });

                                                $( "#datepicker" ).datepicker();
                                                $( "#datepicker2" ).datepicker();
                                            });

                                            $('input.CurrencyInput').on('keyup', function() {
                                                var value = $(this).val().replace(/,/g, '');
                                                value = parseFloat(value).toLocaleString('en-US', {
                                                    style: 'decimal',
                                                    maximumFractionDigits: 2,
                                                });
                                                $(this).val(value);
                                            });

                                            function submitTienUng() {
                                                var soTien = document.getElementById('tienUng');
                                                soTien.value = Number(soTien.value.replace(/[^0-9.-]+/g,""));
                                                $("#save").submit();
                                            }
                                        </script>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


