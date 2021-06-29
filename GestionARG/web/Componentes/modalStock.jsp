<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${alerta}">
    <div style="position: absolute; margin-left:  40%; margin-right: 40%; margin-top:  25%">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Aviso</h5>
                </div>
                <div class="modal-body">
                    Solo contamos con ${stock} /u de este producto: "${producto}"<br>
                    Por favor, modifique la cantidad y vuelva a intentarlo.
                </div>
            </div>
        </div>
    </div>
</c:if>