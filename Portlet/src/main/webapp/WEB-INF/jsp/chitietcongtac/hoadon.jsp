<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp"%>
<portlet:actionURL var="upload">
    <portlet:param name="action" value="upload" />
</portlet:actionURL>

<portlet:renderURL var="homeUrl">
    <portlet:param name="action" value="" />
    <portlet:param name="id" value="${congTacId}" />
</portlet:renderURL>

<liferay-ui:error key="alert-error"
                  message="Bạn cần chọn ảnh trước khi lưu." />

<div class="basic-form-area mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="sparkline12-list shadow-reset">
                    <div class="sparkline12-hd">
                        <div class="main-sparkline12-hd text-center">
                            <h4 class="tieude">${tieuDe}</h4>
                        </div>
                    </div>

                    <div class="sparkline12-graph">
                        <div class="basic-login-form-ad">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="all-form-element-inner">
                                        <form:form action="${upload}" method="post"  enctype="multipart/form-data">
                                            <input type="hidden" name="id" value="${id}">
                                            <div class="form-group-inner m-3">
                                                <div class="row">
                                                    <div class="col-lg-2">
                                                        <label class="login2 pull-right pull-right-pro">Hóa đơn:&nbsp;<label class="text-danger">&#10031;</label></label>
                                                    </div>
                                                    <div class="col-lg-10">
                                                        <input type="file" name="file" id="fileImage">
                                                    </div>
                                                </div>
                                                <img id="thumbnail" alt="Hoa don" class="img-fluid" style="max-width: 60%">
                                            </div>

                                            <div class="form-group-inner m-3">
                                                <div class="login-btn-inner">
                                                    <div class="row">
                                                        <div class="col-lg-3"></div>
                                                        <div class="col-lg-9">
                                                            <div class="login-horizental cancel-wp pull-center">
                                                                <button class="btn btn-sm btn-primary login-submit-cs" type="submit">Lưu</button>
                                                                <button class="btn btn-sm btn-danger login-submit-cs" type="button" onclick="location.href='${homeUrl}'">Quay lại</button>
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

                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function (){
        $('#fileImage').change(function (){
            showImageThumbnail(this);
        })
    })

    function showImageThumbnail(fileInput){
        file = fileInput.files[0];
        reader = new FileReader();
        reader.onload = function (e) {
            $('#thumbnail').attr('src', e.target.result);
        };
        reader.readAsDataURL(file);
    }
</script>


