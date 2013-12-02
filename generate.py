from xhtml2pdf import pisa

def render(header, image):
  return "<td width='200px'><h2>" + header + "</h2><img width='200px' src='" + image + "'></td>"
def renderRow(items):
  return "<tr>" + "".join(map(lambda x: render(x["header"], x["image"]), items)) + "</tr>"
def group(lst, n):
  if len(lst) == 0:
    return []
  else:
    grouped = [[lst[0], lst[1]]] + group(lst[2:], n)
    return grouped
def renderAll(items):
  return "<table>" + "".join(map(renderRow, group(items, 2))) + "</table>"

out = open("test.pdf", "w+b")
item = {"header": "Item", "image": "barcode.png"}
items = map(lambda x: item, range(10))
err = pisa.CreatePDF(renderAll(items), out)
