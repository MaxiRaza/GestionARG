<div class="container">
    <div class="row justify-content-md-center">        
        <div class="col-sm-2">
            <a href="${servelet}?modo=limite&cantidad=${cantidad - (n + 1)}" class="btn btn-info w-75 ${db}" style="width: 30px"><i class="bi bi-dash-lg" style="font-size: 13px"></i></a>
        </div>      
        <div class="col-sm-2">
            <a href="${servelet}?modo=limite&cantidad=${cantidad + (n + 1)}" class="btn btn-info w-75 ${da} " style="width: 30px"><i class="bi bi-plus-lg" style="font-size: 13px"></i></a>
        </div>
    </div> 
</div>