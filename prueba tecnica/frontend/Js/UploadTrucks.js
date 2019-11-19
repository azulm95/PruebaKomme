async function UploadFile() {
    var data = new Object();
    const file =  document.querySelector('#archivo').files[0];
    const filename =  document.querySelector('#archivo').files[0].name;
    let existe = filename.indexOf(".csv");
    if (existe !== -1) {
        const url = 'http://localhost:8080/trucksapi/add';
        data.filename =filename;
        data.email = $('#correo').val();
        data.file = await toBase64(file);
        data.separator = ';';
        data.titles = $('#titles').val();
         $.ajax({
        url: url,
        type: "post",
        data: data
    }).done(function (res) {
        $("#mensaje").html("Respuesta: " + res);
    });
    }
}





const toBase64 = file => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });