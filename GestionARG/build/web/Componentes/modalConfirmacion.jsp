<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${co}">
    <div style="position: absolute; margin-left:  40%;  width: 400px;  height: 100px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
                </div>
                <div class="modal-body">
                    �  Se ha <c:if   test="${accion ==  'Editar'}"  > editado </c:if><c:if   test="${accion == 'Registrar'}" > registrado </c:if> con �xito !
                    </div>
                    <div class="modal-footer">
                            <a href="${servelet}" class= "btn btn-info" style="width: 80px"><i class="bi bi-hand-thumbs-up" style="font-size:  18px"></i></a>
                </div>
            </div>
        </div>
    </div>
</c:if>