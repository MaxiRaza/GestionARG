<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${e}">
        <div style="position: absolute; margin-left:  40%; margin-right: 40%">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
                        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ¿ Seguro que desea eliminar ${nombre} ?
                    </div>
                    <div class="modal-footer">
                        <a href="${servelet}?modo=eliminar&c=c"  class= "btn btn-secondary">Cancelar</a>
                        <a href="${servelet}?modo=eliminar&e=e&c=${c}&id=${id}" class= "btn btn-danger" >Eliminar</a>
                </div>
            </div>
        </div>
    </div> 
</c:if>