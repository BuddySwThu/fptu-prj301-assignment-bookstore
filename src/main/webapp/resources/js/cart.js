function getCart() {
    let request = new XMLHttpRequest();
    request.open('GET', './api/v1/shoppingCart/products/get-cart', true);

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status === 200) {
                renderTable(JSON.parse(request.responseText));
            } else {
                console.log('   ERROR:   ' + request.status + '   ' + request.responseText);
            }
        }
    };

    request.send();
}

function renderTable(productList) {
    let mainFrame = document.getElementById('mainFrame');
    mainFrame.innerHTML = '';
    if (productList === undefined || productList.length === 0) {
        let h2 = document.createElement('h2');
        h2.innerHTML = 'No product in your cart !';
        mainFrame.appendChild(h2);
        return;
    }
    let tableElement = document.createElement('table');
    mainFrame.appendChild(tableElement);

    //render table
    tableElement.setAttribute('border', '2');
    renderTableHeader(tableElement);
    renderTableRows(tableElement, productList);
}

function renderTableHeader(tableElement) {
    const header = ['ID', 'Name', 'Price', 'Quantity', 'Remove Item(s)'];
    let tr = document.createElement('tr');
    tableElement.appendChild(tr);
    for (let i = 0; i < header.length; i++) {
        let th = document.createElement('th');
        th.innerHTML = header[i];
        tr.appendChild(th);
    }
}

function renderTableRows(tableElement, productList) {
    for (let i = 0; i < productList.length; i++) {
        let product = productList[i];
        let tr = document.createElement('tr');
        renderRowData(tr, product.id);
        renderRowData(tr, product.name);
        renderRowData(tr, product.price);
        renderRowData(tr, product.quantity);
        let btnRemoveFromCart = document.createElement('button');
        btnRemoveFromCart.innerHTML = 'Remove';

        btnRemoveFromCart.onclick = function (e) {
            removeProductFromCart(product.id);
        };

        addElementToRowData(tr, btnRemoveFromCart);
        tableElement.appendChild(tr);
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

function removeProductFromCart(productID) {
    let request = new XMLHttpRequest();
    request.open('DELETE', './api/v1/shoppingCart/products/remove-product-from-cart/' + productID, true);

    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE && request.status === 200) {
            alert(JSON.parse(request.responseText).message);
            getCart();
        }
    };

    request.send();
}
