function previewImage(event) {
    var previewContainer = document.getElementById('preview-container');
    previewContainer.innerHTML = ''; // Xóa hình ảnh hiện tại (nếu có)

    var files = event.target.files;
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        var reader = new FileReader();

        reader.onload = function (e) {
            var image = document.createElement('img');
            image.src = e.target.result;
            image.className = 'preview-image';
            previewContainer.appendChild(image);
        }
        reader.readAsDataURL(file);
    }
}

document.addEventListener('DOMContentLoaded', function () {
    var myDiv = document.getElementById('speakers');
    if (myDiv) {
        myDiv.scrollIntoView();
    }
});

function addToCart() {
    var selectedRadio = document.querySelector('input[name="phanLoai"]:checked');
    if (selectedRadio) {
        var productName = selectedRadio.nextElementSibling.textContent.trim();
        var ctspId = selectedRadio.getAttribute("data-ctsp-id");
        var soLuongTon = selectedRadio.getAttribute("data-ctsp-soluong");
        var giaBan = selectedRadio.getAttribute("data-ctsp-gia");
        document.getElementById("addToCartLabel").textContent = productName;
        document.getElementById("QuantityInStock").textContent = soLuongTon + "   Item";
        document.getElementById("price").textContent = giaBan + "   $";

        var quantityInput = document.getElementById("quantityInput");
        var quantity = quantityInput.value;

        var addToCartButton = document.getElementById("addToCartButton");
        addToCartButton.href = "/home/product/detail/addToCart?id=" + ctspId + "&quantity=" + quantity;
    } else {
        alert("Please chose a product !");
    }
}

function buyNow() {
    var selectedRadio = document.querySelector('input[name="phanLoai"]:checked');
    if (selectedRadio) {
        var productName = selectedRadio.nextElementSibling.textContent.trim();
        var ctspId = selectedRadio.getAttribute("data-ctsp-id");
        var soLuongTon = selectedRadio.getAttribute("data-ctsp-soluong");
        var giaBan = selectedRadio.getAttribute("data-ctsp-gia");
        document.getElementById("buyNowLabel").textContent = productName;
        document.getElementById("buyNowQuantityInStock").textContent = soLuongTon + " Item";
        document.getElementById("buyNowPrice").textContent = giaBan + " $";
        var valueOfId = document.getElementById("buyNowCtspId");
        valueOfId.value = ctspId;
    } else {
        alert("Please choose a product!");
    }
}

