function getProducts() {
    let request = new XMLHttpRequest();
    request.open('GET', './api/v1/products/retrieve-books', true);

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status === 200) {
                renderTable(JSON.parse(request.responseText));
            } else {
                console.log('   ERROR:   ' + request.status + '      ' + request.responseText);
            }
        }
    };

    request.send();
}

function renderTable(productList) {
    let mainFrame = document.getElementById('mainFrame');
    if (productList === undefined || productList.length === 0) {
        let h2 = document.createElement('h2');
        h2.innerHTML = 'Shop is out of order. Please come back again!';
        mainFrame.appendChild(h2);
        return;
    }
    let tableElement = document.createElement('table');
    mainFrame.appendChild(tableElement);
    tableElement.setAttribute('border', '2');
    renderTableHeader(tableElement);
    renderTableRows(tableElement, productList);
}

function renderTableHeader(tableElement) {
    const header = ['ID', 'Name', 'Price', 'Add to cart'];
    let tr = document.createElement('tr');
    tableElement.appendChild(tr);
    for (let i = 0; i < header.length; i++) {
        let th = document.createElement('th');
        th.innerHTML = header[i];
        tr.appendChild(th);
    }
}

function renderRowData(rowElement, value) {
    let td = document.createElement('td');
    td.innerHTML = value;
    rowElement.appendChild(td);
}

function addElementToRowData(rowElement, element) {
    let td = document.createElement('td');
    td.appendChild(element);
    rowElement.appendChild(td);
}

function renderTableRows(tableElement, productList) {
    for (let i = 0; i < productList.length; i++) {
        let product = productList[i];
        let tr = document.createElement('tr');
        renderRowData(tr, product.id);
        renderRowData(tr, product.name);
        renderRowData(tr, product.price);
        let btnAddToCart = document.createElement('button');
        btnAddToCart.innerHTML = 'Add to cart';

        btnAddToCart.onclick = function (e) {
            addProductToCart(product.id);
        };

        addElementToRowData(tr, btnAddToCart);
        tableElement.appendChild(tr);
    }
}

function addProductToCart(productID) {
    let request = new XMLHttpRequest();
    request.open('POST', './api/v1/shoppingCart/products/add-product-to-cart/' + productID, true);

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status === 200) {
                alert(JSON.parse(request.responseText).message);
            } else {
                console.log('   ERROR:   ' + request.responseText);
            }
        }
    };

    request.send();
}
