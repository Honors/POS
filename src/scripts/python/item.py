class Item:
  def render(self):
    return "<td width='200px'><h2>" + self.header + "</h2><img width='200px' src='" + self.image + "'></td>" 
  def __init__(self, header, image):
    self.header = header
    self.image = image

