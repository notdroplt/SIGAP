const toObj = tuples => tuples.reduce((acc, [key, value]) => {
    acc[key] = value;
    return acc;
  }, {});

let form_el = document.querySelector("button#formSubmit");

const submit = (endpoint, ev) => {
    const inputs = ev.target.parentNode.querySelectorAll("input, select");
    const vals = Array.from(inputs).map(x => [x.id, x.value]);
    const obj = toObj(vals);
    console.log(obj);
    fetch(endpoint, {
        method: "POST",
        body: JSON.stringify(obj)
    })
}