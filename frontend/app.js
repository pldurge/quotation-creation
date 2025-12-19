function generateQuote() {
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const carId = document.getElementById("carId").value;
  const gst = document.getElementById("gst").value;
  const discount = document.getElementById("discount").value;

  const services = [];
  document
    .querySelectorAll("input[type=checkbox]:checked")
    .forEach((cb) => services.push(parseInt(cb.value)));

  const body = {
    gst: parseFloat(gst),
    discount: parseFloat(discount),
    serviceIds: services,
  };

  fetch(`http://localhost:8080/quotes/${carId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Basic " + btoa(username + ":" + password),
    },
    body: JSON.stringify(body),
  })
    .then((res) => res.text())
    .then((data) => {
      document.getElementById("result").innerText = data;
    })
    .catch((err) => {
      document.getElementById("result").innerText = err;
    });
}
