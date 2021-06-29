<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${e}">
        <div style="position: absolute; margin-left:  40%; margin-right: 40%; margin-top:  -2%">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
                    </div>
                    <div class="modal-body">
                        ¿ Seguro que desea eliminar ${nombre} ?
                    </div>
                    <div class="modal-footer">
                        <a href="${servelet}?modo=eliminar" class= "btn btn-secondary" style="width: 80px"><i class="bi bi-x-circle" style="font-size:  19px"></i></a>
                        <a href="${servelet}?modo=eliminar&e=e&c=${c}&id=${id}" class= "btn btn-danger" style="width: 80px"><i class="bi bi-trash" style="font-size:  19px"></i></a>
                </div>
            </div>
        </div>
    </div> 
</c:if>