const productsData = [
    { id: 1, name: 'Product 1', price: 20 },
    { id: 2, name: 'Product 2', price: 30 },
    { id: 3, name: 'Product 3', price: 25 },
];

// Sample cart data
const cartData = [];

// Function to render products
function renderProducts() {
    const productsSection = document.getElementById('products');
    productsSection.innerHTML = '';

    productsData.forEach(product => {
        const productElement = document.createElement('div');
        productElement.classList.add('product');
        productElement.innerHTML = `
            <h3>${product.name}</h3>
            <p>$${product.price}</p>
            <button onclick="addToCart(${product.id})">Add to Cart</button>
        `;
        productsSection.appendChild(productElement);
    });
}

// Function to render the shopping cart
function renderCart() {
    const cartList = document.getElementById('cart-items');
    cartList.innerHTML = '';

    cartData.forEach(item => {
        const cartItem = document.createElement('li');
        cartItem.textContent = `${item.name} - $${item.price}`;
        cartList.appendChild(cartItem);
    });
}

// Function to add a product to the cart
function addToCart(productId) {
    const product = productsData.find(item => item.id === productId);
    if (product) {
        cartData.push({ ...product });
        renderCart();
    }
}

// Initial rendering
renderProducts();
renderCart();