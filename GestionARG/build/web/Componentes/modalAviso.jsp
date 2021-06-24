<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${co}">
    <div style="position: absolute; margin-left:  40%; margin-right: 40%">
        <div class="modal" id="modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
                        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ¡  Se ha <c:if   test="${accion}"  > editado </c:if><c:if   test="${accion}"  > registrado </c:if> con éxito !
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal" >Aceptar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</c:if>