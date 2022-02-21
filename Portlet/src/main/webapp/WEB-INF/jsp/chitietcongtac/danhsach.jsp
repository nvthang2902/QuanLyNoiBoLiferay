<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<portlet:renderURL var="addduan">
    <portlet:param name="action" value="add"/>
</portlet:renderURL>
<portlet:renderURL var="addTienUng">
    <portlet:param name="action" value="addTienUng"/>
</portlet:renderURL>
<portlet:renderURL var="uploadHoaDon">
    <portlet:param name="action" value="upload"/>
</portlet:renderURL>
<portlet:actionURL var="deleteduan">
    <portlet:param name="action" value="delete"/>
</portlet:actionURL>
<portlet:actionURL var="sendMail">
    <portlet:param name="action" value="sendMail"/>
</portlet:actionURL>

<portlet:actionURL var="duyetPhi">
    <portlet:param name="action" value="duyetPhi"/>
</portlet:actionURL>
<portlet:actionURL var="hoanThanh">
    <portlet:param name="action" value="hoanThanh"/>
</portlet:actionURL>

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
                        <button class="btn btn-danger w-10 p-1" title="Quay lại"
                                onclick="location.href='${congTacUrl}&id=${duAnId}'">
                            <i class="fas fa-arrow-left"></i>
                        </button>
                        <div class="main-sparkline8-hd text-center">
                            <c:choose>
                                <c:when test="${listView.isEmpty()}">
                                    <h4 class="tieude">Dự án:&nbsp;${tenDuAn}</h4>
                                </c:when>
                                <c:when test="${listView != null}">
                                    <h4 class="tieude">Dự
                                        án:&nbsp;${listView.get(0).getCongTac().getDuAn().getTenDuAn()}</h4>
                                </c:when>
                            </c:choose>
                            <h4 class="tieude">Công tác:&nbsp;${nhiemVu}</h4>
                        </div>
                    </div>

                    <c:if test="${(userId == nhanVienId) && (trangThai == 'MOICAPNHAT' || trangThai == 'DATUCHOI' || trangThai == 'DAUNG')}">
                        <div class="col-md-12 form-group text-right">
                            <button class="btn btn-info"
                                    onclick="location.href='${addduan}&congTac.id=${id}'">
                                Thêm mới
                            </button>
                        </div>

                    </c:if>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">

                            <table id="table" class="table-fit table table-striped table-borderd">
                                <thead>
                                <tr>

                                    <th class="align-middle text-center">STT</th>
                                    <th class="align-middle text-left">Tên chi phí</th>
                                    <th class="align-middle text-left">Đơn vị tính</th>
                                    <th class="align-middle text-center">Số lượng</th>
                                    <th class="align-middle text-right">Đơn giá</th>
                                    <th class="align-middle text-right">Thành tiền</th>
                                    <c:if test="${trangThai == 'DAUNG' || trangThai == 'DAHOANUNG' || trangThai == 'CHOHOANUNG'}">
                                        <th class="align-middle text-center">Hóa đơn</th>
                                        <th class="align-middle text-center">Trạng thái</th>
                                    </c:if>
                                    <th class="align-middle text-center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${listView}" varStatus="num">
                                    <tr>
                                        <td class="align-middle text-center">${num.index +1}</td>
                                        <td class="align-middle text-left">${item.tenChiPhi}</td>
                                        <td class="align-middle text-left">${item.donViTinh}</td>
                                        <td class="align-middle text-center">${item.soLuong}</td>
                                        <td class="align-middle text-right">
                                            <fmt:formatNumber type="number" pattern="###,###" value="${item.donGia}"/>
                                        </td>
                                        <td class="align-middle text-right thanhTien">
                                            <fmt:formatNumber type="number" pattern="###,###"
                                                              value="${item.thanhTien}"/>
                                        </td>

                                        <c:if test="${trangThai == 'DAUNG' || trangThai == 'DAHOANUNG' || trangThai == 'CHOHOANUNG'}">
                                            <td class="align-middle text-center">
                                                <img src="${imageUrl}/${item.hoaDon}"
                                                     class="rounded mx-auto d-block" width="100px" height="100px"
                                                     data-toggle="modal"
                                                     data-target="#myModalImage${item.hoaDon}">

                                                <div class="modal fade" id="myModalImage${item.hoaDon}" role="dialog"
                                                     tabindex="-1"
                                                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-full-height modal-right modal-notify modal-info modal-xl"
                                                         role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header h-1">
                                                                <p></p>
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-label="Close">
                                                <span aria-hidden="true" class="white-text"><i
                                                        class="fas fa-window-close"></i></span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">

                                                                <img src="${imageUrl}/${item.hoaDon}"
                                                                     class="img-fluid">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <c:if test="${item.trangThai == 'true'}">
                                                <td class="align-middle text-center text-success da-duyet">Đã duyệt</td>
                                            </c:if>
                                            <c:if test="${item.trangThai == 'false'}">
                                                <td class="align-middle text-center text-danger da-duyet">Chờ duyệt</td>
                                            </c:if>
                                        </c:if>
                                        <td class="align-middle text-center">
                                            <c:forEach var="role" items="${userRoles}">
                                                <c:if test="${((role.getName() == 'Administrator') || (role.getName() == 'Kế toán')) && (item.trangThai == 'false') &&(trangThai == 'CHOHOANUNG')}">
                                                    <button class="btn btn-success w-6 p-1" title="Duyệt"
                                                            type="button"
                                                            onclick="myFunctionDuyet(0,${item.id})"><i
                                                            class="fas fa-check"></i>
                                                    </button>
                                                    <button class="btn btn-info w-6 p-1" title="Chỉnh sửa"
                                                            onclick="location.href='${addduan}&id=${item.id}&congTac.id=${id}'">
                                                        <i class="fas fa-pen"></i></button>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${(userId == nhanVienId) && ((trangThai == 'MOICAPNHAT' || trangThai == 'DATUCHOI' || trangThai == 'DAUNG') && item.trangThai == 'false')}">
                                                <button class="btn btn-info w-6 p-1" title="Chỉnh sửa"
                                                        onclick="location.href='${addduan}&id=${item.id}&congTac.id=${id}'">
                                                    <i class="fas fa-pen"></i></button>
                                                <button class="btn btn-danger w-6 p-1" title="Xóa"
                                                        onclick="location.href='${deleteduan}&id=${item.id}'"><i
                                                        class="fas fa-trash-alt"></i></button>
                                                <button class="btn btn-danger w-6 p-1" title="Thêm hóa đơn"
                                                        onclick="location.href='${uploadHoaDon}&id=${item.id}'"><i
                                                        class="fas fa-images"></i></button>
                                            </c:if>
                                        </td>
                                    </tr>


                                </c:forEach>

                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="align-middle text-right">Tổng tiền</td>
                                    <td class="align-middle text-right tongTien">

                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <c:if test="${trangThai == 'DAUNG' || trangThai == 'DAHOANUNG' || trangThai == 'CHOHOANUNG'}">
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="align-middle text-right">Đã ứng</td>
                                        <td class="align-middle text-right" id="daUng">
                                            <fmt:formatNumber type="number" pattern="###,###"
                                                              value="${listView.get(0).congTac.tienUng}"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="align-middle text-right">Tổng tiền đã duyệt</td>
                                        <td class="align-middle text-right" id="tongTienDaDuyet">

                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="align-middle text-right">Số tiền phải hoàn</td>
                                        <td class="align-middle text-right" id="hoanCongTacPhi">

                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>
                            <c:forEach var="nv" items="${nhanVienDuAnDTOList}" varStatus="num">
                                <c:forEach var="nvda" items="${nhanVienDuAns}" varStatus="num">
                                    <c:if test="${nv.nhanVien.id == nvda.nhanVien.id && (trangThai == 'MOICAPNHAT' || trangThai == 'DATUCHOI')}">
                                        <div class="d-flex justify-content-center">
                                            <button class="btn btn-sm btn-primary login-submit-cs m-2" type="button"
                                                    data-toggle="modal"
                                                    data-target="#myModalDuyet" onclick="myFunction(0,${id})">Duyệt
                                            </button>
                                            <button class="btn btn-sm btn-danger login-submit-cs m-2" type="button"
                                                    data-toggle="modal"
                                                    data-target="#myModal" onclick="myFunctionTuChoi(1,${id})">Từ chối
                                            </button>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            <c:if test="${(userId == nhanVienId) && (trangThai == 'DAUNG')}">
                                <div class="d-flex justify-content-center">
                                    <button class="btn btn-sm btn-primary login-submit-cs m-2" type="button"
                                            data-toggle="modal"
                                            data-target="#myModalChoHoanPhi" onclick="myFunctionChoHoanUng(2,${id})">
                                        Hoàn thành
                                    </button>
                                </div>
                            </c:if>
                            <c:forEach var="role" items="${userRoles}">
                                <c:if test="${((role.getName() == 'Administrator') || (role.getName() == 'Kế toán')) &&(trangThai == 'CHOHOANUNG')}">
                            <div class="d-flex justify-content-center">
                                    <button class="btn btn-sm btn-primary login-submit-cs m-2"
                                            type="button"
                                            onclick="myFunctionHoanThanh(3,${id})">Hoàn thành
                                    </button>
                            </div>
                                </c:if>
                            </c:forEach>
                            <form method="post" action="${duyetPhi}" id="duyetPhi">
                                <input id="trangThai" name="trangThai" type="hidden" value="0"/>
                                <input id="chiTietCongTacId" name="chiTietCongTacId" type="hidden"/>
                            </form>

                            <form method="post" action="${hoanThanh}" id="hoanThanh">
                                <input id="status3" name="status" type="hidden" value="0"/>
                                <input id="congTacId3" name="congTacId" type="hidden"/>
                            </form>
                            <div class="modal fade" id="myModalDuyet" role="dialog" tabindex="-1" data-backdrop="false">
                                <div class="modal-dialog modal-full-height modal-right modal-notify modal-info"
                                     role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <p class="heading lead">Email duyệt công tác phí</p>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true" class="white-text"><i
                                                        class="fas fa-window-close"></i></span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="${sendMail}" id="send-form">
                                                <input id="status" name="status" type="hidden" value="0"/>
                                                <input id="congTacId" name="congTacId" type="hidden"/>
                                                <div class="form-group">
                                                    <label class="col-form-label">Người gửi</label>
                                                    <input class="form-control"
                                                           name="from"
                                                           type="text"
                                                           value="${emailGui}"
                                                           label="From"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Người nhận</label>
                                                    <input class="form-control"
                                                           name="to"
                                                           type="text"
                                                           value="${emailNhan}"
                                                           label="To"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Tiêu đề</label>
                                                    <input class="form-control"
                                                           name="subject"
                                                           type="text"
                                                           value="Duyệt công tác phí"
                                                           label="Subject"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Nội dung</label>

                                                    <textarea class="form-control text-start"
                                                              name="body">Dự án: ${tenDuAn}&#10;Nhiệm vụ: ${nhiemVu}

                                                    </textarea>
                                                </div>
                                                <div class="form-group d-flex justify-content-center">
                                                    <button type="button"
                                                            class="btn btn-primary waves-effect waves-light"
                                                            id="submit-button"
                                                            value="Send Mail"
                                                            onclick="submitForm();">
                                                        Gửi
                                                        <i class="fa fa-paper-plane ml-1"></i>
                                                    </button>
                                                    <button type="button"
                                                            class="btn btn-outline-warning waves-effect ml-2"
                                                            data-dismiss="modal">Hủy
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal fade" id="myModalChoHoanPhi" role="dialog" tabindex="-1"
                                 data-backdrop="false">
                                <div class="modal-dialog modal-full-height modal-right modal-notify modal-info"
                                     role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <p class="heading lead">Email xác nhận đã hoàn công tác phí</p>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true" class="white-text"><i
                                                        class="fas fa-window-close"></i></span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="${sendMail}" id="send-form-choHoanPhi">
                                                <input id="status2" name="status" type="hidden" value="0"/>
                                                <input id="congTacId2" name="congTacId" type="hidden"/>
                                                <div class="form-group">
                                                    <label class="col-form-label">Người gửi</label>
                                                    <input class="form-control"
                                                           name="from"
                                                           type="text"
                                                           value="${emailGui}"
                                                           label="From"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Người nhận</label>
                                                    <input class="form-control"
                                                           name="to"
                                                           type="text"
                                                           value="${emailNhan}"
                                                           label="To"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Tiêu đề</label>
                                                    <input class="form-control"
                                                           name="subject"
                                                           type="text"
                                                           value="Đã hoàn thành hoàn công tác phí"
                                                           label="Subject"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Nội dung</label>

                                                    <textarea class="form-control text-start"
                                                              name="body">Dự án: ${tenDuAn}&#10;Nhiệm vụ: ${nhiemVu}

                                                    </textarea>
                                                </div>
                                                <div class="form-group d-flex justify-content-center">
                                                    <button type="button"
                                                            class="btn btn-primary waves-effect waves-light"
                                                            id="submit-choHoanUng"
                                                            value="Send Mail"
                                                            onclick="submitFormChoHoanUng();">
                                                        Gửi
                                                        <i class="fa fa-paper-plane ml-1"></i>
                                                    </button>
                                                    <button type="button"
                                                            class="btn btn-outline-warning waves-effect ml-2"
                                                            data-dismiss="modal">Hủy
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal fade" id="myModal" role="dialog" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-full-height modal-right modal-notify modal-info"
                                     role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <p class="heading lead">Email từ chối công tác phí</p>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true" class="white-text"><i
                                                        class="fas fa-window-close"></i></span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="${sendMail}" id="send-form-tuChoi">
                                                <input id="status1" name="status" type="hidden" value="0"/>
                                                <input id="congTacId1" name="congTacId" type="hidden"/>
                                                <div class="form-group">
                                                    <label class="col-form-label">Người gửi</label>
                                                    <input class="form-control"
                                                           name="from"
                                                           type="text"
                                                           value="${emailGui}"
                                                           label="From"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Người nhận</label>
                                                    <input class="form-control"
                                                           name="to"
                                                           type="text"
                                                           value="${emailNhan}"
                                                           label="To"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Tiêu đề</label>
                                                    <input class="form-control"
                                                           name="subject"
                                                           type="text"
                                                           value="Từ chối công tác phí"
                                                           label="Subject"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Nội dung</label>

                                                    <textarea class="form-control text-start"
                                                              name="body">Dự án: ${tenDuAn}&#10;Nhiệm vụ: ${nhiemVu}&#10;Lý do:

                                                    </textarea>
                                                </div>
                                                <div class="form-group d-flex justify-content-center">
                                                    <button type="button"
                                                            class="btn btn-primary waves-effect waves-light"
                                                            id="submit-button1"
                                                            value="Send Mail"
                                                            onclick="submitFormTuChoi();">
                                                        Gửi
                                                        <i class="fa fa-paper-plane ml-1"></i>
                                                    </button>
                                                    <button type="button"
                                                            class="btn btn-outline-warning waves-effect ml-2"
                                                            data-dismiss="modal">Hủy
                                                    </button>
                                                </div>
                                            </form>
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
</div>
</div>
<script>

    function myFunction(status, id) {
        document.getElementById("status").value = status;
        document.getElementById("congTacId").value = id;
        submitForm();
    }

    function myFunctionDuyet(status, id) {
        document.getElementById("trangThai").value = status;
        document.getElementById("chiTietCongTacId").value = id;
        $("#duyetPhi").submit();
    }

    function myFunctionHoanThanh(status, id) {
        document.getElementById("status3").value = status;
        document.getElementById("congTacId3").value = id;
        $("#hoanThanh").submit();
    }

    function myFunctionChoHoanUng(status, id) {
        document.getElementById("status2").value = status;
        document.getElementById("congTacId2").value = id;
        submitFormChoHoanUng();
    }

    function myFunctionTuChoi(status, id) {
        document.getElementById("status1").value = status;
        document.getElementById("congTacId1").value = id;
    }

    function submitForm() {
        $("#close-button").trigger("click");
        $("#send-form").submit();
    }

    function submitFormChoHoanUng() {
        $("#close-button").trigger("click");
        $("#send-form-choHoanPhi").submit();
    }

    function submitFormTuChoi() {
        $("#close-button").trigger("click");
        $("#send-form-tuChoi").submit();
    }

    var total = $('td.tongTien'),
        td_thanhTien = $('td.thanhTien');

    total.text(function () {
        var sum = 0
        td_thanhTien.each(function () {
            sum += +Number($(this).text().replace(/[^0-9.-]+/g, ""));
        });
        var formatter = new Intl.NumberFormat('en-US', {
            style: 'decimal',
            maximumFractionDigits: 2,
        });
        return formatter.format(sum);
    })

    var tongTienDaDuyet = $('#tongTienDaDuyet'),
        td_thanhTienDaDuyet = $('td.thanhTien');
    td_trangThai = $('.da-duyet')
    tongTienDaDuyet.text(function () {
        var sum = 0
        for (var i = 0; i < td_thanhTienDaDuyet.length; i++) {
            if (td_trangThai[i].innerHTML == "Đã duyệt") {
                sum += +Number(td_thanhTienDaDuyet[i].innerHTML.replace(/[^0-9.-]+/g, ""));
            }

        }

        var formatter = new Intl.NumberFormat('en-US', {
            style: 'decimal',
            maximumFractionDigits: 2,
        });
        return formatter.format(sum);
    })

    var hoanCongTacPhi = $('#hoanCongTacPhi'),
        soTienDaUng = $('#daUng'),
        tongTienDaDuyet = $('#tongTienDaDuyet')
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