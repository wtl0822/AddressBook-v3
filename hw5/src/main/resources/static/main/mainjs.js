function add(elem) {
    var form;
    form = $('<form />', {
        action: '/add',
        method: 'POST',
        style: 'display: none;'
    });
    form.appendTo('body').submit();
}


function alter(elem) {
    let tr = elem.parentNode.parentNode
    let contactName = tr.children[0].innerHTML
    let row = elem.parentNode.parentNode.rowIndex - 1
    let temp = document.createElement("form")
    temp.action = "/alter"
    temp.method = "post"
    temp.style.display = "none"

    let opt = document.createElement("textarea")
    opt.name = "name"
    opt.value = contactName
    temp.appendChild(opt)
    document.body.appendChild(temp)

    temp.submit()
    return temp
}

// 需要获得其所在行的联系人姓名
function del(elem) {
    let tr = elem.parentNode.parentNode
    let contactName = tr.children[0].innerHTML
    $.post('/del', {name: contactName}, sucdel(tr))
}

// 更新前端数据的方法
function sucdel(tr) {
    tr.parentNode.removeChild(tr)  // 在前端中删除这一行
}

function callBack() {
    // 暂时不需要执行回调操作
}


