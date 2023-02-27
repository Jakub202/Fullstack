module.exports = function(req, res, next) {
    if (req.method === "POST" && req.path === "/api/form") {
      res.json({ message: "success" });
    } else {
      next();
    }
  };