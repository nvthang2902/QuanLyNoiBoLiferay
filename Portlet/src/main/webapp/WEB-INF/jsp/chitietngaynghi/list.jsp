<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:renderURL var="addNewURL">
    <portlet:param name="action" value="addNew"/>
</portlet:renderURL>
<portlet:actionURL var="deleteURL">
    <portlet:param name="action" value="delete"/>
</portlet:actionURL>
<portlet:actionURL var="sendMail">
    <portlet:param name="action" value="sendMail"/>
</portlet:actionURL>

<liferay-ui:success key="form-success"
                    message="Your request was successfully!."/>
<liferay-ui:error key="form-error"
                  message="Your request was failed!"/>

<div class="admin-dashone-data-table-area">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline8-list shadow-reset">
                    <div class="sparkline8-hd">
                        <div class="main-sparkline8-hd text-center">
                            <h1 class="title">Ngay nghi</h1>
                        </div>
                    </div>
                    <c:forEach var="role" items="${userRoles}">
                        <c:if test="${role.getName() == 'Nhân viên'}">
                            <div class="col-md-12 form-group text-left">
                                <button class="btn btn-info" title="Thêm mới"
                                        onclick="location.href='${addNewURL}&id=0'">
                                    <i class="fad fa-plus-circle"></i></button>
                            </div>
                        </c:if>
                    </c:forEach>
                    <div class="col-lg-12">
                        <div class="datatable-dashv1-list custom-datatable-overright">
                            <table id="table" class="table-fit table table-striped table-borderd">
                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th onclick="sortTable(1)">Họ tên</th>
                                    <th>Ngày xin nghỉ</th>
                                    <th>Số ngày nghỉ</th>
                                    <th>Buổi nghỉ</th>
                                    <th>Trạng thái</th>
                                    <th>Trạng thái duyệt</th>
                                    <th>Lý do</th>
                                    <th class="align-middle text-center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="role" items="${userRoles}">
                                    <c:if test="${role.getName() == 'Administrator'}">
                                        <c:forEach var="nn" items="${listView}" varStatus="num">
                                            <tr>
                                                <td class="align-middle text-center">${num.index +1}</td>
                                                <td class="align-middle text-left">${nn.nhanVien.tenNhanVien}</td>
                                                <td class="align-middle text-left">
                                                    <fmt:formatDate value="${nn.ngayXinNghi}"
                                                                    pattern="dd/MM/yyyy"/></td>
                                                <td class="align-middle text-left" id="loopS">${nn.soNgayNghi}</td>

                                                <c:choose>
                                                    <c:when test="${nn.buoiNghi == 'SANG'}">
                                                        <td class="align-middle text-center text-success">Sáng</td>
                                                    </c:when>
                                                    <c:when test="${nn.buoiNghi == 'CHIEU'}">
                                                        <td class="align-middle text-center text-success">Chiều</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="align-middle text-center text-warning">Cả ngày</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${nn.trangThai =='COPHEP'}">
                                                        <td class="align-middle text-center text-success">Có phép</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="align-middle text-center text-danger">Không phép</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${nn.tinhTrangDuyet=='CHODUYET'}">
                                                        <td class="align-middle text-center text-success">Chờ duyệt</td>
                                                    </c:when>
                                                    <c:when test="${nn.tinhTrangDuyet=='DADUYET'}">
                                                        <td class="align-middle text-center text-primary">Đã duyệt</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="align-middle text-center text-danger">Từ chối</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <td class="align-middle text-left">${nn.lyDo}</td>
                                                <td class="align-middle text-center">
                                                    <button class="btn btn-success"
                                                            title="Đồng ý"
                                                            data-toggle="modal" data-target="#myModalDongY"
                                                            onclick="functionChiTietNgayNghi(0,${nn.id})">
                                                        <i class="fas fa-check-circle"></i></button>
                                                    <button class="btn btn-danger"
                                                            title="Từ chối"
                                                            data-toggle="modal" data-target="#myModalTuChoi"
                                                            onclick="functionChiTietNgayNghiTuChoi(1,${nn.id})">
                                                        <i class="fas fa-times-circle"></i></button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            </hr>
                                            <td colspan="3" class="text-center"><b><i>Tổng ngày xin nghỉ</i></b></td>
                                            <td id="totalS"></td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${role.getName() == 'Nhân viên'}">
                                        <c:forEach var="nv" items="${CTNNByUser}" varStatus="num">
                                            <tr>
                                                <td class="align-middle text-center">${num.index +1}</td>
                                                <td class="align-middle text-left">${nv.nhanVien.tenNhanVien}</td>
                                                <td class="align-middle text-left">
                                                    <fmt:formatDate value="${nv.ngayXinNghi}"
                                                                    pattern="dd/MM/yyyy"/></td>
                                                <td class="soNgayNghi align-middle text-left" id="soNgayNghi">${nv.soNgayNghi}</td>

                                                <c:choose>
                                                    <c:when test="${nv.buoiNghi == 'SANG'}">
                                                        <td class="align-middle text-center text-success">Sáng</td>
                                                    </c:when>
                                                    <c:when test="${nv.buoiNghi == 'CHIEU'}">
                                                        <td class="align-middle text-center text-success">Chiều</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="align-middle text-center text-warning">Cả ngày</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${nv.trangThai =='COPHEP'}">
                                                        <td class="align-middle text-center text-success">Có phép</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="align-middle text-center text-danger">Không phép</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${nv.tinhTrangDuyet=='CHODUYET'}">
                                                        <td class="align-middle text-center text-success">Chờ duyệt</td>
                                                    </c:when>
                                                    <c:when test="${nv.tinhTrangDuyet=='DADUYET'}">
                                                        <td class="da-duyet align-middle text-center text-primary">Đã duyệt</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="align-middle text-center text-danger">Từ chối</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <td class="align-middle text-left">${nv.lyDo}</td>
                                                <td class="align-middle text-center">
                                                    <button class="btn btn-outline-info"
                                                            title="Xem thông tin"
                                                            onclick="location.href='${addNewURL}&id=${nv.id}'">
                                                        <i class="fas fa-eye"></i></button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            </hr>
                                            <td colspan="3" class="text-center"><b><i>Tổng ngày xin nghỉ</i></b></td>
                                            <td class="total" id="total"></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="modal fade" id="myModalDongY" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Gửi mail thông báo</h5>
                                            <button type="button" class="close btn btn-danger" data-dismiss="modal"
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="${sendMail}" id="send-form">
                                                <input id="status" name="status" type="hidden"
                                                       value="0"/>
                                                <input id="chiTietId" name="chiTietId" type="hidden"/>

                                                <div class="form-group">
                                                    <label class="col-form-label">Người gửi:</label>
                                                    <input type="text" class="form-control" name="from" disabled="true" type="hidden" value="${emailGui}">
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Người nhận:</label>
                                                    <input type="text" class="form-control" name="to" disabled="true" type="hidden" value="${emailNhan}">
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-form-label">Chủ đề:</label>
                                                    <input type="text" class="form-control" name="subject"
                                                           value="Đồng ý">
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Nội dung:</label>
                                                    <textarea class="form-control" name="body"
                                                              id="message-text">
                                                        Ngày:${ngayXinNghi}&#10;Lý do: đồng ý!
                                                    </textarea>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" data-dismiss="modal"
                                                    title="Quay lại">
                                                <i class="fas fa-arrow-circle-left"></i>
                                            </button>
                                            <button type="button" class="btn btn-success" title="Gửi mail"
                                                    onclick="submitFormChiTiet();" value="Send Mail">
                                                <i class="fas fa-file-import"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal fade" id="myModalTuChoi" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" >Gửi mail thông báo</h5>
                                            <button type="button" class="close btn btn-danger" data-dismiss="modal"
                                                    aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="post" action="${sendMail}" id="send-formTuChoi">
                                                <input id="status1" name="status" type="hidden"
                                                       value="0"/>
                                                <input id="chiTietId1" name="chiTietId" type="hidden"/>

                                                <div class="form-group">
                                                    <label class="col-form-label">Người gửi:</label>
                                                    <input type="text" class="form-control" name="from" disabled="true" type="hidden" value="${emailGui}"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Người nhận:</label>
                                                    <input type="text" class="form-control" name="to" disabled="true" type="hidden" value="${emailNhan}"/>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-form-label">Chủ đề:</label>
                                                    <input type="text" class="form-control" name="subject"
                                                           value="Từ chối">
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-form-label">Nội dung:</label>
                                                    <textarea class="form-control" name="body">Ngày:${ngayXinNghi}&#10;Lý do:từ chối</textarea>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" data-dismiss="modal"
                                                    title="Quay lại">
                                                <i class="fas fa-arrow-circle-left"></i>
                                            </button>
                                            <button type="button" class="btn btn-success" title="Gửi mail"
                                                    onclick="submitFormChiTietTuChoi();" value="Send Mail">
                                                <i class="fas fa-file-import"></i>
                                            </button>
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
    function functionChiTietNgayNghi(status, id) {
        document.getElementById("status").value = status;
        document.getElementById("chiTietId").value = id;
    }
    function functionChiTietNgayNghiTuChoi(status, id) {
        document.getElementById("status1").value = status;
        document.getElementById("chiTietId1").value = id;
    }

    function submitFormChiTiet() {
        $("#send-form").submit();
        $("#close-button").trigger("click");
    }
    function submitFormChiTietTuChoi() {
        $("#send-formTuChoi").submit();
        $("#close-button").trigger("click");
    }

    function sortTable(n) {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById("table");
        switching = true;
        dir = "asc";
        while (switching) {
            switching = false;
            rows = table.rows;
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                if (dir == "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir == "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchcount++;
            } else {
                if (switchcount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }
    $(function() {
        var TotalValue = 0;
        $("tr #loopS").each(function(index,value){
            currentRow = parseFloat($(this).text());
            TotalValue += currentRow
        });
        document.getElementById('totalS').innerHTML = TotalValue;
    });

    var soNgayNghi = $('#soNgayNghi'),
        TotalValue = $('td.total');
    td_trangThai = $('.da-duyet')
    soNgayNghi.text(function () {
        var sum = 0
        for (var i = 0; i < TotalValue.length; i++) {
            if (td_trangThai[i].innerHTML == "Đã duyệt") {

                sum += +Number(TotalValue[i].innerHTML);
                console.log(sum)  ;
            }
        }
        console.log(sum);
    });
</script>
