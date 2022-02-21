<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    $(document).ready(function () {
        $('input[id$=tbDate]').datepicker({dateFormat: 'dd/mm/yy'});
    });

</script>
<style>
    .modal-backdrop fade show {
        display: none;
    }
</style>
<portlet:actionURL var="addNewURL">
    <portlet:param name="action" value="addNew"/>
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value=""/>
</portlet:renderURL>
<portlet:actionURL var="sendMail">
    <portlet:param name="action" value="sendMail"/>
</portlet:actionURL>

<c:if test="${empty item.id or item.id == 0}">
    <c:set var="title">
        Thêm
    </c:set>
</c:if>
<c:if test="${item.id > 0}">
    <c:set var="title">
        Chỉnh sửa
    </c:set>

</c:if>

<liferay-ui:error key="alert-error"
                  message="Your request was failed!."/>

<div class="basic-form-area mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline12-list shadow-reset">
                    <div class="sparkline12-hd">
                        <div class="main-sparkline12-hd text-center">
                            <h1 class="title">${title}</h1>
                            <div class="sparkline12-outline-icon">
                                <span class="sparkline12-collapse-link"><i class="fa fa-chevron-up"></i></span>
                                <span><i class="fa fa-wrench"></i></span>
                                <span class="sparkline12-collapse-close"><i class="fa fa-times"></i></span>
                            </div>
                        </div>
                    </div>
                    <!-- end title -->

                    <!-- content -->
                    <div class="sparkline12-graph">
                        <div class="basic-login-form-ad">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="all-form-element-inner">
                                        <form:form action="${addNewURL}" method="post" name="submitForm"
                                                   modelAttribute="item">
                                            <form:hidden path="id"></form:hidden>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="ngayXinNghi"
                                                                    class="login2 pull-right pull-right-pro">
                                                            Ngày xin nghỉ<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="ngayXinNghi" id='tbDate' class="form-control"
                                                                    placeholder="dd/MM/yyyy"/>
                                                        <form:errors path="ngayXinNghi" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="soNgayNghi"
                                                                    class="login2 pull-right pull-right-pro">
                                                            Số ngày nghỉ<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="soNgayNghi" id="soNgayNghi"
                                                                    type="number" value="0.5" step="0.5" min="0.5" max="1"
                                                                    class="form-control"/>
                                                        <form:errors path="soNgayNghi" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="buoiNghi"
                                                                    class="login2 pull-right pull-right-pro">Buổi nghỉ<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:radiobutton path="buoiNghi" id="buoiNghi"
                                                                          checked="checked" value="SANG"/>Sáng
                                                        <form:radiobutton path="buoiNghi" id="buoiNghi" value="CHIEU"/>Chiều
                                                        <form:radiobutton path="buoiNghi" id="buoiNghi" value="CANGAY"/>Cả ngày
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="trangThai"
                                                                    class="login2 pull-right pull-right-pro">Trạng thái<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:radiobutton path="trangThai" id="trangThai"
                                                                          checked="checked" value="COPHEP"/>Nghỉ phép
                                                        <form:radiobutton path="trangThai" id="trangThai"
                                                                          value="KHONGPHEP"/>Không phép&ensp;
                                                    </div>
                                                </div>
                                            </div>

                                            <form:hidden path="tinhTrangDuyet" id="tinhTrangDuyet" value="CHODUYET"/>

                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="lyDo"
                                                                    class="login2 pull-right pull-right-pro">Lý do<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:textarea rows="4" path="lyDo" id="lyDo"
                                                                       class="form-control"/>
                                                        <form:errors path="lyDo" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <label class="login2 pull-right pull-right-pro">Họ
                                                            tên<span class="text-danger">* :</span></label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <label class="form-control">${nhanVien.tenNhanVien}</label>
                                                        <form:hidden path="nhanVien.id" value="${nhanVien.id}"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="login-btn-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3"></div>
                                                        <div class="col-lg-9">
                                                            <div class="login-horizental cancel-wp pull-center">
                                                                <button class="btn btn-sm btn-warning login-submit-cs"
                                                                        title="Gửi mail"
                                                                        type="button"
                                                                        data-toggle="modal" data-target="#myModal">
                                                                    <i class="fas fa-envelope"></i>
                                                                </button>
                                                                <button class="btn btn-sm btn-success login-submit-cs"
                                                                        title="Lưu"
                                                                        type="submit">
                                                                    <i class="fas fa-save"></i>
                                                                </button>
                                                                <button class="btn btn-sm btn-danger login-submit-cs"
                                                                        title="Quay lại"
                                                                        type="button"
                                                                        onclick="location.href='${homeUrl}'">
                                                                    <i class="fas fa-arrow-circle-left"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="modal fade bd-example-modal-lg" id="myModal" tabindex="-1"
                                                 role="dialog"
                                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title">Gửi mail thông báo</h5>
                                                            <button type="button" class="close btn-danger"
                                                                    data-dismiss="modal"
                                                                    aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form method="post" action="${sendMail}" id ="send-form">
                                                                <div class="form-group">
                                                                    <label class="col-form-label">Người gửi:</label>
                                                                    <input type="text" class="form-control" name="from" value="${emailGui}">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-form-label">Người nhận</label>:
                                                                    <input type="text" class="form-control" name="to" value="${emailNhan}">                                                         </div>
                                                                <div class="form-group">
                                                                    <label class="col-form-label">Chủ đề</label>:
                                                                    <input class="form-control" name="subject" type="text" label="Chủ đề" value="Xin nghỉ phép"/>
                                                                </div>
                                                                <div class="form-group">
                                                                        <label class="col-form-label">Nội dung</label>:
                                                                        <textarea class="form-control" name="body"
                                                                                  id="message-text" type="text" label="Nội dung">Ngày xin nghỉ:${ngayXinNghi}&#10;Buổi nghỉ:${buoiNghi}&#10;Lý do:...</textarea>
                                                                </div>
                                                            </form>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-danger"
                                                                        data-dismiss="modal" title="Quay lại">
                                                                    <i class="fas fa-arrow-circle-left"></i>
                                                                </button>
                                                                <button type="submit" class="btn btn-success" id="close-button"
                                                                        title="Gửi mail" id="submit-button"
                                                                        value="Send Mail"
                                                                        onclick="submitForm();">
                                                                    <i class="fas fa-file-import"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end content -->
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function functionChiTietNgayNghi(status, id) {
        document.getElementById("status").value = status;
        document.getElementById("chiTietId").value = id;
    }

    function submitForm() {
        $("#send-form").submit();
        $("#close-button").trigger("click");
    }

</script>