<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp"%>
<%--<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">--%>
<%--<link rel="stylesheet" href="/resources/demos/style.css">--%>
<%--<script src="https://code.jquery.com/jquery-3.6.0.js"></script>--%>
<%--<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>--%>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>

<portlet:actionURL var="addNewURL">
    <portlet:param name="action" value="addNew" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value="" />
</portlet:renderURL>

<c:if test="${empty item.id or item.id == 0}">
    <c:set var="title">
        Thêm nhân viên
    </c:set>
</c:if>
<c:if test="${item.id > 0}">
    <c:set var="title">
        Chỉnh sửa
    </c:set>

</c:if>

<liferay-ui:error key="alert-error"
                  message="Your request was failed!." />

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
                                        <form:form action="${addNewURL}" method="post" name="submitForm" id="save"
                                                   modelAttribute="item">
                                            <form:hidden path="id"></form:hidden>
                                            <form:hidden path="userId"></form:hidden>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="tenNhanVien" class="login2 pull-right pull-right-pro">Tên nhân viên<span class="text-danger">* </span>:</form:label>
                                                    </div>

                                                    <div class="col-lg-6">
                                                        <form:input path="tenNhanVien" id="tenNhanVien" class="form-control" disabled=""/>
                                                        <form:errors path="tenNhanVien" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="ngaySinh" class="login2 pull-right pull-right-pro">Ngày Sinh<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="ngaySinh" type="text" id="datepicker" class="form-control" placeholder="MM/dd/yyyy" disabled="" />
                                                        <form:errors path="ngaySinh" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="gioiTinh" class="login2 pull-right pull-right-pro">Giới tính<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:radiobutton path="gioiTinh" id="gioiTinh" value="true"/>Nam
                                                        <form:radiobutton path="gioiTinh" id="gioiTinh" value="false"/>Nữ
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="sdt" class="login2 pull-right pull-right-pro">Số điện thoại<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="sdt" id="sdt" class="form-control" />
                                                        <form:errors path="sdt" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="luongChinhThuc" class="login2 pull-right pull-right-pro">Lương chính thức<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="luongChinhThuc" id="luongChinhThuc" class="CurrencyInput form-control"/>
                                                        <form:errors path="luongChinhThuc" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <form:label path="thueTNCN" class="login2 pull-right pull-right-pro">Thuế TNCN<span class="text-danger">* </span>:</form:label>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <form:input path="thueTNCN" id="thueTNCN" class="CurrencyInput form-control"/>
                                                        <form:errors path="thueTNCN" cssClass="error text-danger" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group-inner m-3">
                                                <div class="login-btn-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3"></div>
                                                        <div class="col-lg-9">
                                                            <div class="login-horizental cancel-wp pull-center">
                                                                <button class="btn btn-sm btn-success login-submit-cs" title="Lưu"
                                                                        type="button"
                                                                        onclick="submitNhanVien();">
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
<script type="text/javascript">
    $('input.CurrencyInput').on('keyup', function() {
        var value = $(this).val().replace(/,/g, '');
        value = parseFloat(value).toLocaleString('en-US', {
            style: 'decimal',
            maximumFractionDigits: 2,
        });
        $(this).val(value);
    });

    function submitNhanVien() {
        var luongChinhThuc = document.getElementById('luongChinhThuc');
        var thueTNCN = document.getElementById('thueTNCN');

        luongChinhThuc.value = Number(luongChinhThuc.value.replace(/[^0-9.-]+/g,""));
        thueTNCN.value = Number(thueTNCN.value.replace(/[^0-9.-]+/g,""));
        $("#save").submit();
    }
</script>
