const fileInput = document.getElementById('imagem-item');
let img_b64 = null;

fileInput.addEventListener('change', (event) => {
  const file = event.target.files[0];

  if (!file) {
    console.error("nenhum arquivo selecionado!");
    return;
  }

  const reader = new FileReader();

  reader.onload = (e) => {img_b64 = e.target.result};
  reader.readAsDataURL(file);
});

function notificacao(message) {
  const container = document.getElementById("notification-container");

  const notification = document.createElement("div");
  notification.className = "notification";
  if (message.sucesso) {
    notification.textContent = "Item cadastrado com sucesso! Retornando..."
    notification.classList.add("suc-notif")
  } else {
    notification.textContent = message.motivo;
    notification.classList.add("fail-notif")
  }

  container.appendChild(notification);

  setTimeout(() => {
    notification.classList.add("show");
  }, 10);

  setTimeout(() => {
    notification.classList.remove("show");
    if (message.sucesso)
        window.location.href = "/SIGAP/home.jsp"
    setTimeout(() => container.removeChild(notification), 300);
  }, 1200);
}

const itens = ["nome", "cor", "marca", "lugar", "desc", "campus"]

const nome_el = document.getElementById("nome-item");
const cor_el = document.getElementById("cor-item");
const marca_el = document.getElementById("marca-item");
const lugar_el = document.getElementById("lugar-item");
const desc_el = document.getElementById("desc-item");
const campus_el = document.getElementById("campus-item");

function submit (endpoint, ev) {
  const nome_v = nome_el.value;
  if (nome_v == null) console.error("nome_v == null");

  const cor_v = cor_el.value;
  if (cor_v == null) console.error("cor_v == null");

  const marca_v = marca_el.value;
  if (marca_v == null) console.error("marca_v == null");

  const lugar_v = lugar_el.value;
  if (lugar_v == null) console.error("lugar_v == null");

  const desc_v = desc_el.value;
  if (desc_v == null) console.error("desc_v == null");

  const campus_v = campus_el.value;
  if (campus_v == null) console.error("campus_v == null");

const possuiNome = document.getElementById('possui-nome').checked;
  const nome_pessoa = document.getElementById('nome').value;
  const sobrenome_pessoa = document.getElementById('sobrenome').value;

  const obj = {
    "nome": nome_v,
    "cor": cor_v,
    "marca": marca_v,
    "lugar": lugar_v,
    "desc": desc_v,
    "campus": campus_v,
    "imagem": img_b64
  };

  if (possuiNome) {
    if (nome_pessoa) obj.nomePessoa = nome_pessoa;
    if (sobrenome_pessoa) obj.sobrenomePessoa = sobrenome_pessoa;
  }

  console.log(obj)

  fetch(endpoint, {
    method: "POST",
    body: JSON.stringify(obj)
  }).then(res => res.json())
    .then(notificacao)
}

function toggleNomeSobrenome() {
    const checkbox = document.getElementById('possui-nome');
    const container = document.getElementById('nome-sobrenome-container');
    container.style.display = checkbox.checked ? 'block' : 'none';
}